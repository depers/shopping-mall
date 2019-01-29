package com.fmall.task;

import com.fmall.common.Const;
import com.fmall.service.IOrderService;
import com.fmall.util.PropertiesUtil;
import com.fmall.util.RedisShardedPoolUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by 冯晓 on 2019/1/28.
 */

@Component
@Slf4j
public class CloseOrderTask {

    @Autowired
    private IOrderService iOrderService;

    //@Scheduled(cron = "0 */1 * * * ?") //每一分钟（每一分钟的整数倍）
    public void closeOrderTaskV1(){
        log.info("关闭订单定时任务启动");
        int hour = Integer.parseInt(PropertiesUtil.getProperty("close.order.task.time.hour"));
        iOrderService.closeOrder(hour);
        log.info("关闭订单定时任务结束");
    }


    @Scheduled(cron = "0 */1 * * * ?")
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

    private void closeOrder(String lockName){
        RedisShardedPoolUtil.expire(lockName, 50); // 有效期为50秒，防止死锁
        log.info("获取{},ThreadName:{}", lockName, Thread.currentThread().getName());
        long lockTimeout = Long.parseLong(PropertiesUtil.getProperty("lock.timeOut", "5000"));
        int hour = Integer.parseInt(PropertiesUtil.getProperty("close.order.task.time.hour"));
        //iOrderService.closeOrder(hour);
        RedisShardedPoolUtil.del(lockName); //释放锁
        log.info("释放{},ThreadName:{}",lockName, Thread.currentThread().getName());
        log.info("===========================================");
    }
}
