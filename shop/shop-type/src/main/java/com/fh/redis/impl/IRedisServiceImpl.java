package com.fh.redis.impl;

import com.fh.redis.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service("redis")
public class IRedisServiceImpl implements IRedisService {

            @Autowired
            private RedisTemplate redisTemplate;

    @Override
    public void setShopType(String key, Object  value) {

        redisTemplate.opsForValue().set(key,value);
    }

    @Override
    public Boolean isExistKey(String key) {

        return redisTemplate.hasKey(key);
    }

    @Override
    public Object getStringValueKey(String key) {

        return redisTemplate.opsForValue().get(key);
    }
}
