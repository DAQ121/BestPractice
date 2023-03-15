package com.daiaoqi.middleware;

import DTO.Student;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @description: 使用springbootdata集成的redis测试
 * @author: daiaoqi
 * @date: 2022/9/13
 **/
@SpringBootTest
class springDataRedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void testString() {
        stringRedisTemplate.opsForValue().set("name", "daiaoqi");
        System.out.println(stringRedisTemplate.opsForValue().get("name"));
    }

    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    void testObj() throws JsonProcessingException {
        Student student = new Student();
        student.setName("daq");
        student.setAge(23);

        String json = mapper.writeValueAsString(student);

        // 存对象
        stringRedisTemplate.opsForValue().set("hv", json);

        // 取对象，进行反序列化
        String res = stringRedisTemplate.opsForValue().get("hv");
        Student stu = mapper.readValue(res, Student.class);
        System.out.println(stu);
    }

}
