package com.mysystem.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Component
public class RedisUtils implements ApplicationContextAware {

    private static RedisUtils redisUtils;

    @Autowired
    private RedisTemplate redisTemplate;


    public static boolean tryLock(String key, Long lockTime) {
        return redisUtils.redisTemplate.opsForValue().setIfAbsent(key, "success", Duration.ofMillis(lockTime));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        redisUtils = applicationContext.getBean(RedisUtils.class);
    }
}
