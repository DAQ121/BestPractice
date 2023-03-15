package com.daiaoqi.middleware.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

/**
 * @description: redis连接池
 * @author: daiaoqi
 * @date: 2022/9/13
 **/
public class JedisConnectionFactory {

    private static final JedisPool jedisPool;

    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 最大连接
        jedisPoolConfig.setMaxTotal(8);
        // 最大空闲连接
        jedisPoolConfig.setMaxIdle(8);
        // 晨小空闲连接
        jedisPoolConfig.setMinIdle(0);
        // 设置最长等待时间
        jedisPoolConfig.setMaxWait(Duration.ofMinutes(2000));

        jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379, 1000);
    }

    public static Jedis getJedis() {
        return jedisPool.getResource();
    }
}
