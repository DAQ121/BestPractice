package com.daiaoqi.middleware;

import com.daiaoqi.middleware.utils.JedisConnectionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;

public class JedisTest {

    private Jedis jedis;

    @BeforeEach
    void setUp() {
        jedis = JedisConnectionFactory.getJedis();
        jedis.select(0);
        System.out.println("建立连接");
    }

    @Test
    void testString() {

        jedis.set("name", "daiaoqi");
        System.out.println(jedis.get("name"));
    }

    @Test
    void testHash() {
        // 插入单个
        jedis.hset("hv:product:1","name", "daiaoqi");
        System.out.println(jedis.hget("hv:product:1", "name"));

        // 插入多个
        HashMap<String, String> map = new HashMap<>();
        map.put("name", "daiaoqi");
        map.put("age", "23");
        map.put("sex", "男");

        jedis.hset("hv:pro:1", map);
        System.out.println(jedis.hget("hv:pro:1", "age"));
    }

    @AfterEach
    void tearDown() {
        if (jedis != null) {
            jedis.close();
            System.out.println("释放连接");
        }
    }
}
