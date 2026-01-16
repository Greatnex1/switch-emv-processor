package com.ubagroup.switchemv.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

/*Tracks how many tx per user per time window.*/
@Service
@RequiredArgsConstructor
public class VelocityService {

    private final RedisTemplate<String, Integer> redis;

    private static final int LIMIT = 5;
    private static final Duration WINDOW = Duration.ofMinutes(1);

    public int incrementAndGet(Long userId) {

        String key = "tx:velocity:user:" + userId;

        Integer count = redis.opsForValue().get(key);

        if (count == null) {
            redis.opsForValue().set(key, 1, WINDOW);
            return 1;
        } else {
            redis.opsForValue().increment(key);
            return count + 1;
        }
    }

    public boolean isBlocked(Long userId) {
        Integer count = redis.opsForValue().get("tx:velocity:user:" + userId);
        return count != null && count > LIMIT;
    }
}
