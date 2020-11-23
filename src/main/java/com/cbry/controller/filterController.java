package com.cbry.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cbry.nomal.JedisPoolDemo;

import redis.clients.jedis.Jedis;

@Controller
public class filterController {

	@RequestMapping("/filter")
	public String toIndex() {
		System.err.println("进入controller");
		return "index";
	}
	
	@RequestMapping("/sessionTest")
	public void sessionTest(HttpServletRequest request , HttpServletResponse response) {
		HttpSession session = request.getSession();
		System.err.println("生成的sessionId为：" + session.getId());
		Jedis jedis = JedisPoolDemo.initJedisPool().getResource();
		jedis.hset("session","sessionId", session.getId());
		jedis.hset("session","userName", "cbry");
		jedis.expire("session", 600);
	}
	
	@RequestMapping("/isLogin")
	public void isLoginTest(HttpServletRequest request , HttpServletResponse response) {
		HttpSession session = request.getSession();
		Jedis jedis = JedisPoolDemo.initJedisPool().getResource();
		String sessionIdFromRedis = jedis.hget("session", "sessionId");
		String userName = jedis.hget("session", "userName");
		if (session.getId().equals(sessionIdFromRedis)) {
			System.out.println("同一会话，用户名为：" + userName);
		}else {
			System.err.println("error！！！不同用户！！！");
		}
	}
}
