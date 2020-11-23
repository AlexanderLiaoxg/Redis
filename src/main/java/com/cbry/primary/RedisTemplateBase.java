package com.cbry.primary;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

public class RedisTemplateBase {
	@Autowired
    private RedisTemplate<String, Object> redisTemplate;

	
	private void doEasy() {
		// TODO Auto-generated method stub
		redisTemplate.opsForValue().set("redisTemplate", "hello redisTemplate , i will use u as a tool");
		System.err.println(redisTemplate.opsForValue().get("redisTemplate"));
	}
	
	//Redis hash 是一个 string 类型的 field（字段） 和 value（值） 的映射表，hash 特别适合用于存储对象。 Redis 中每个 hash 可以存储 232 - 1 键值对
	private void hash() {
		Map userInfo = new HashMap<>();
		
		userInfo.put("name", "Lucy");
		userInfo.put("description", "a cool girl");
		
		//put ： (map的key, hashkey（name等），value
		redisTemplate.opsForHash().put("userInfo", "sex", "Female");
		
		//putAll：放入map对象
		redisTemplate.opsForHash().putAll("userInfo", userInfo);
		
		//values参数key对应的map值，返回结果为List<Object> ，keys方法也类似
		System.err.println(redisTemplate.opsForHash().values("userInfo").toString());
		
		//delete删除对应的字段
		 redisTemplate.opsForHash().delete("userInfo", "name");
		 
		//确定字段是否在map中存在，bool类型
		 Boolean is_save = redisTemplate.opsForHash().hasKey("userInfo", "name");
		 
		 //multiGet,获取多个值
		 Collection<Object> keys = new ArrayList<>();
	        keys.add("name");
	        keys.add("sex");
	        System.out.println(redisTemplate.opsForHash().multiGet("userInfo", keys));
		 
	}
	
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
	
	public static void main(String[] args) {
		new RedisTemplateBase().doEasy();
	}
}
