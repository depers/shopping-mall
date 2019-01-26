package com.fmall.common;

import com.fmall.util.PropertiesUtil;
import redis.clients.jedis.*;
import redis.clients.util.Hashing;
import redis.clients.util.Sharded;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 冯晓 on 2019/1/26.
 */

public class RedisShardedPool {

    private static ShardedJedisPool pool;  // sharded jedis连接池，声明为statis是为了连接池在启动tomcat时就要加载出来
    private static Integer maxTotal = Integer.parseInt(PropertiesUtil.getProperty("redis.max.total", "20"));  // 控制jedis和redis server之间的最大连接数
    private static Integer maxIdle = Integer.parseInt(PropertiesUtil.getProperty("redis.min.total", "10"));  // jedisPool中最大的idle状态（空闲的）的jedis实例的个数
    private static Integer minIdle = Integer.parseInt(PropertiesUtil.getProperty("redis.min.idle", "2"));  // jedisPool中最大的idle状态（空闲的）的jedis实例的个数
    private static Boolean testBorrow = Boolean.parseBoolean(PropertiesUtil.getProperty("redis.test.borrow", "true"));  // 在borrow一个jedis实例的时候，是否要进行验证操作，如果赋值为true，则得到的jedis实例肯定是可用的
    private static Boolean testReturn = Boolean.parseBoolean(PropertiesUtil.getProperty("redis.test.return", "true"));  // 在return一个jedis实例的时候，是否要进行验证操作，如果赋值为true，则放回jedispool的jedis实例肯定是可用的

    private static String redis1Ip = PropertiesUtil.getProperty("redis1.ip");
    private static Integer redis1Port = Integer.parseInt(PropertiesUtil.getProperty("redis1.port"));

    private static String redis2Ip = PropertiesUtil.getProperty("redis2.ip");
    private static Integer redis2Port = Integer.parseInt(PropertiesUtil.getProperty("redis2.port"));

    private static void initPool(){
        JedisPoolConfig config = new JedisPoolConfig();

        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);

        config.setTestOnBorrow(testBorrow);
        config.setTestOnReturn(testReturn);

        config.setBlockWhenExhausted(true);  // 连接耗尽的时候，是否阻塞，false会抛出异常，true阻塞直到超时，默认为true

        JedisShardInfo info1 = new JedisShardInfo(redis1Ip, redis1Port, 1000*2);
        JedisShardInfo info2 = new JedisShardInfo(redis2Ip, redis2Port, 1000*2);

        List<JedisShardInfo> jedisShardInfoList = new ArrayList<JedisShardInfo>(2);
        jedisShardInfoList.add(info1);
        jedisShardInfoList.add(info2);

        pool = new ShardedJedisPool(config, jedisShardInfoList, Hashing.MURMUR_HASH, Sharded.DEFAULT_KEY_TAG_PATTERN); //分片策略Hashing.MURMUR_HASH就是一致性算法
    }

    static {
        initPool();
    }

    public static ShardedJedis getJedis(){
        return pool.getResource();
    }

    public static void returnResource(ShardedJedis jedis){
        pool.returnResource(jedis);
    }

    public static void returnBrokenResource(ShardedJedis jedis){
        pool.returnBrokenResource(jedis);
    }

    public static void main(String[] args) {
        ShardedJedis jedis = pool.getResource();

        for (int i=0; i<10; i++){
            jedis.set("key"+i, "value"+i);
        }
        returnResource(jedis);

        System.out.println("program is end");
    }
}
