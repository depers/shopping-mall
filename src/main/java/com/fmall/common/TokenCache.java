package com.fmall.common;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;


/**
 * Created by 冯晓 on 2017/6/22.
 */
@Slf4j
public class TokenCache {

    public static String TOKEN_PREFIX = "token_";

    // LRU算法
    // initialCapacity(1000)设置缓存的初始化容量
    private static LoadingCache<String, String> loadCache = CacheBuilder.newBuilder()
            .initialCapacity(1000)
            .maximumSize(10000)
            .expireAfterAccess(12, TimeUnit.HOURS)
            .build(new CacheLoader<String, String>() {
                // 默认的数据加载实现，当调用get取值的时候，如果key没有对应的值，就调这个方法进行加载。
                @Override
                public String load(String s) throws Exception {
                    return "null";
                }
            });

    public static void setKey(String key, String value){
        loadCache.put(key, value);
    }


    public static String getKey(String key){
        String value = null;
        try{
            value = loadCache.get(key);
            if("null".equals(value)){
                return null;
            }
            return value;

        }catch (Exception e){
            log.error("localCache get error!", e);
        }
        return null;
    }


}
