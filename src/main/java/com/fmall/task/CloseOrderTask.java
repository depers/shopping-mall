package com.fmall.task;

import com.fmall.common.Const;
import com.fmall.service.IOrderService;
import com.fmall.util.PropertiesUtil;
import com.fmall.util.RedisShardedPoolUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * Created by 冯晓 on 2019/1/28.
 */

@Component
@Slf4j
public class CloseOrderTask {

    @Autowired
    private IOrderService iOrderService;

    // 没有使用kill进程的方式关闭tomcat，而是使用tomcat的shutdown命令关闭tomcat的时候。tomcat容器在关闭之前会调用这个方法
    // 解决的问题：
    //    例如：在执行Long setnxResult = RedisShardedPoolUtil.setnx(Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK, String.valueOf(System.currentTimeMillis()+lockTimeout));
    //    代码后我们停止的tomcat服务，并没有给锁设置时间，那么在分布式的情况下，就会产生死锁。使得另一个进程无法获得锁
    // 存在的缺点：1)如果我们有上千个锁，在shutdown的时候都要执行这个方法，会使shutdown的时间过长
    //            2)还有就是如果采用kill进程的方式关闭tomcat的话，该方法根本就不会执行
    @PreDestroy
    public void delLock(){
        RedisShardedPoolUtil.del(Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK);
    }

    //@Scheduled(cron = "0 */1 * * * ?") //每一分钟（每一分钟的整数倍）
    public void closeOrderTaskV1(){
        log.info("关闭订单定时任务启动");
        int hour = Integer.parseInt(PropertiesUtil.getProperty("close.order.task.time.hour"));
        iOrderService.closeOrder(hour);
        log.info("关闭订单定时任务结束");
    }


    //@Scheduled(cron = "0 */1 * * * ?")
    public void closeOrderTaskV2(){
        log.info("关闭订单定时任务启动");
        long lockTimeout = Long.parseLong(PropertiesUtil.getProperty("lock.timeOut", "5000"));

        Long setnxResult = RedisShardedPoolUtil.setnx(Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK, String.valueOf(System.currentTimeMillis()+lockTimeout));
        if (setnxResult != null && setnxResult.intValue() == 1){
            // 如果返回值是1，代表设置成功，获取锁
            closeOrder(Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK);
        }else{
            log.info("没有获得分布式锁:{}", Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK);
        }

        log.info("关闭订单定时任务结束");
    }


    @Scheduled(cron = "0 */1 * * * ?")
    public void closeOrderTaskV3(){
        log.info("关闭订单定时任务启动");

        long lockTimeout = Long.parseLong(PropertiesUtil.getProperty("lock.timeOut", "5000"));

        Long setnxResult = RedisShardedPoolUtil.setnx(Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK, String.valueOf(System.currentTimeMillis()+lockTimeout));
        if (setnxResult != null && setnxResult.intValue() == 1){
            // 如果返回值是1，代表设置成功，获取锁
            closeOrder(Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK);
        }else{
            // 未获取到锁，继续判断，判断时间戳，看是否可以重置并获得到锁
            String lockValueStr = RedisShardedPoolUtil.get(Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK);
            if (lockValueStr != null && System.currentTimeMillis() > Long.parseLong(lockValueStr)){
                // 因为我们是集群，之前的lockValueStr可能已经被改掉了，所以这里要获取新值
                String getSetResult = RedisShardedPoolUtil.getSet(Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK, String.valueOf(System.currentTimeMillis()+lockTimeout));
                // 使用lockValueStr和getSetResult做判断看是否能获取锁
                // 当key没有旧值，即另外一个进程把key删除了，key不存在时，就返回nil --> 获取锁
                // 当key有旧值且之前没有进程修改过他 --> 获取锁
                if (getSetResult == null || (getSetResult != null && StringUtils.equals(lockValueStr, getSetResult))){
                    // 获取锁
                    closeOrder(Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK);
                }else{
                    log.info("没有获取到分布式锁:{}", Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK);
                }
            }else{
                log.info("没有获取到分布式锁:{}", Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK);
            }
        }
        log.info("关闭订单定时任务结束");
    }

    private void closeOrder(String lockName){
        RedisShardedPoolUtil.expire(lockName, 5); // 有效期为50秒，防止死锁
        log.info("获取{},ThreadName:{}", lockName, Thread.currentThread().getName());
        long lockTimeout = Long.parseLong(PropertiesUtil.getProperty("lock.timeOut", "5000"));
        int hour = Integer.parseInt(PropertiesUtil.getProperty("close.order.task.time.hour"));
        //iOrderService.closeOrder(hour);
        RedisShardedPoolUtil.del(lockName); //释放锁
        log.info("释放{},ThreadName:{}",lockName, Thread.currentThread().getName());
        log.info("===========================================");
    }
}
