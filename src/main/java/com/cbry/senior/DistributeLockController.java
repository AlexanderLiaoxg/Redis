package com.cbry.senior;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DistributeLockController {
	
	@Autowired
	RedisTemplate redisTemplate;
	
	@RequestMapping("submitOrder")
	public String submitOrder() {
		
		
		return "index";
	}
}
