package com.han.shiro_spring_boot.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisCacheUtil {
    @Autowired
    private static RedisTemplate<String, Object> redisTemplate;

    /**
     * 普通缓存获取
     * @param key 键
     * @return 值
     */
    public static Object get(String key) {
        return key == null ? null:redisTemplate.opsForValue().get(key); //redisTemplate对象一直为null
    }

    public static RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }
}
