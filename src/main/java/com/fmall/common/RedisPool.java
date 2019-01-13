package com.fmall.common;

import com.fmall.util.PropertiesUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPool {

    private static JedisPool pool;  // jedis连接池，声明为statis是为了连接池在启动tomcat时就要加载出来
    private static Integer maxTotal = Integer.parseInt(PropertiesUtil.getProperty("redis.max.total", "20"));  // 控制jedis和redis server之间的最大连接数
    private static Integer maxIdle = Integer.parseInt(PropertiesUtil.getProperty("redis.min.total", "10"));  // jedisPool中最大的idle状态（空闲的）的jedis实例的个数
    private static Integer minIdle = Integer.parseInt(PropertiesUtil.getProperty("redis.min.idle", "2"));  // jedisPool中最大的idle状态（空闲的）的jedis实例的个数
    private static Boolean testBorrow = Boolean.parseBoolean(PropertiesUtil.getProperty("redis.test.borrow", "true"));  // 在borrow一个jedis实例的时候，是否要进行验证操作，如果赋值为true，则得到的jedis实例肯定是可用的
    private static Boolean testReturn = Boolean.parseBoolean(PropertiesUtil.getProperty("redis.test.return", "true"));  // 在return一个jedis实例的时候，是否要进行验证操作，如果赋值为true，则放回jedispool的jedis实例肯定是可用的

    private static String redisIp = PropertiesUtil.getProperty("redis.ip");
    private static Integer redisPort = Integer.parseInt(PropertiesUtil.getProperty("redis.port"));

    private static void initPool(){
        JedisPoolConfig config = new JedisPoolConfig();

        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);

        config.setTestOnBorrow(testBorrow);
        config.setTestOnReturn(testReturn);

        config.setBlockWhenExhausted(true);  // 连接耗尽的时候，是否阻塞，false会抛出异常，true阻塞直到超时，默认为true

        pool = new JedisPool(config, redisIp, redisPort, 1000*2);
    }

    static {
        initPool();
    }

    public static Jedis getJedis(){
        return pool.getResource();
    }

    public static void returnResource(Jedis jedis){
        pool.returnResource(jedis);
    }

    public static void returnBrokenResource(Jedis jedis){
        pool.returnBrokenResource(jedis);
    }

    public static void main(String[] args) {
        Jedis jedis = pool.getResource();
        jedis.set("xiaokey", "xiaovalue");
        returnResource(jedis);

        pool.destroy();  // 临时调用，销毁链接池中的所有连接
        System.out.println("program is end");
    }
}

