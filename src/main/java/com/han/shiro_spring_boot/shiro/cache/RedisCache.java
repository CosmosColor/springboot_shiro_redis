package com.han.shiro_spring_boot.shiro.cache;



import com.han.shiro_spring_boot.utils.ApplicationContextUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Collection;
import java.util.Set;


//自定义redis缓存实现
public class RedisCache<K, V> implements Cache<K, V> {

    private Object cacheName;

    public RedisCache(String cacheName) {
        this.cacheName=cacheName;
    }

    @Override
    public V get(K k) throws CacheException {
        System.out.println("key:" + k);
        System.out.println("redisKey"+k.toString()+"redisval"+ (V) getRedisTemplate().opsForHash().get(this.cacheName,k.toString()));
        return (V) getRedisTemplate().opsForHash().get(this.cacheName,k.toString());
    }

    @Override
    public V put(K k, V v) throws CacheException {
        System.out.println("key" + k);
        System.out.println("val" + v);
        getRedisTemplate().opsForHash().put(this.cacheName,k.toString(), v);
        return null;
    }

    @Override
    public V remove(K k) throws CacheException {
        System.out.println("========remove=========");
        return (V)getRedisTemplate().opsForHash().delete(this.cacheName,k.toString());
    }

    @Override
    public void clear() throws CacheException {
        System.out.println("========clear==========");
       getRedisTemplate().delete(this.cacheName);
    }

    @Override
    public int size() {
        return getRedisTemplate().opsForHash().size(this.cacheName).intValue();
    }

    @Override
    public Set<K> keys() {
        return getRedisTemplate().opsForHash().keys(this.cacheName);
    }

    @Override
    public Collection<V> values() {
        return getRedisTemplate().opsForHash().values(this.cacheName);
    }
    private RedisTemplate getRedisTemplate(){
        RedisTemplate redisTemplate= ApplicationContextUtils.getBean("redisTemplate",RedisTemplate.class);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
