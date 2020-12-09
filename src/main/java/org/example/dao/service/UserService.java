package org.example.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hyq on 2020/12/9 22:54.
 */
@Service
public class UserService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public String getString(String key) {
//        String val = "RedisTemplate";
//        redisTemplate.opsForValue().set(key, val);
        Duration timeout = Duration.ofSeconds(60);
        redisTemplate.opsForValue().set("timeout", "timeout", timeout);
//        System.out.println(redisTemplate.opsForValue().get("timeout"));
        redisTemplate.opsForValue().set("expire", "expire");
        redisTemplate.expire("expire", timeout);
        List<String> keys = new ArrayList<>();
        keys.add("timeout");
//        keys.add("expire");
//        keys.add("key3");
        redisTemplate.delete(keys);
        System.out.println(redisTemplate.getExpire("timeout"));

        Long inc = redisTemplate.opsForValue().increment("inc");
        System.out.println(inc);
        return (String) redisTemplate.opsForValue().get(key);
    }
}
