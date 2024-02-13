package com.example.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class SimpleController {
    // 문자열 Key와 문자열로 구성된 Value를 다루기 위한 RedisTemplate
    private final StringRedisTemplate redisTemplate;

    @PutMapping("string")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setString(
            @RequestParam("key")
            String key,
            @RequestParam("value")
            String value
    ) {
        // Redis에 String을 저장하고 싶다
        // ValueOperations: Redis 기준 문자열 작업을 위한 클래스
        ValueOperations<String, String> operations
                = redisTemplate.opsForValue();
        // SET key value
        operations.set(key, value);

        /*// List를 위한 클래스
        ListOperations<String, String> listOperations
                = redisTemplate.opsForList();
        listOperations.leftPush(key, value);
        listOperations.leftPop(key);

        // Set을 위한 클래스
        SetOperations<String, String> setOperations
                = redisTemplate.opsForSet();
        setOperations.add(key, value);*/
    }

    @GetMapping("string")
    public String getString(
            @RequestParam("key")
            String key
    ) {
        ValueOperations<String, String> operations
                = redisTemplate.opsForValue();
        // GET key
        String value = operations.get(key);
        if (value == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return value;

        /*SetOperations<String, String> operations
                = redisTemplate.opsForSet();
        return operations.members(key);*/
    }
}
