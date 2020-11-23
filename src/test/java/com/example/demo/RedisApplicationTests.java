package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.cbry.RedisApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisApplication.class)
public class RedisApplicationTests {
	@Resource
    public RedisTemplate<String, Object> redisTemplate;

	//@Test
	public void doEasy() {
		// TODO Auto-generated method stub
		
		redisTemplate.opsForValue().set("redisTemplate", "hello redisTemplate , i will use u as a tool");
		System.err.println(redisTemplate.opsForValue().get("redisTemplate"));
	}
	
	//@Test
	public void hash() {
		Map userInfo = new HashMap<>();
		
		userInfo.put("name", "Lucy");
		userInfo.put("description", "a cool girl");
		
		redisTemplate.opsForHash().put("userInfo", "sex", "Female");
		redisTemplate.opsForHash().putAll("userInfo", userInfo);
		System.err.println(redisTemplate.opsForHash().values("userInfo").toString());
	}
	
	@Test
	public void list() {
		List userInfo = new ArrayList();
		
		redisTemplate.delete("userInfo");
		userInfo.add("Nancy");
		userInfo.add("a sunny girl");
		redisTemplate.opsForList().leftPush("userInfo", "first String before nancy");
		redisTemplate.opsForList().leftPush("userInfo", "second String before nancy");
		//左右插入l/rpush , all 是集合
		redisTemplate.opsForList().rightPushAll("userInfo", userInfo);
		
		System.err.println(redisTemplate.opsForList().leftPop("userInfo").toString());
		System.err.println(redisTemplate.opsForList().rightPop("userInfo").toString());
		System.err.println("在出栈后的userInfoList列表数据：" + redisTemplate.opsForList().range("userInfo", 0, -1));
	}
	

}
