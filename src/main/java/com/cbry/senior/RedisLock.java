package com.cbry.senior;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisLock {
	@Autowired
	RedisTemplate redisTemplate;
}
