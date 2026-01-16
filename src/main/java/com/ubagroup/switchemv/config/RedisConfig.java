package com.ubagroup.switchemv.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Integer> redisTemplate(
            RedisConnectionFactory factory) {

        RedisTemplate<String, Integer> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        return template;
    }

    @Bean
    public RedisCacheConfiguration cacheConfig() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(30))
                .disableCachingNullValues();
    }
}
