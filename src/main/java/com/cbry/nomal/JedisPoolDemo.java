package com.cbry.nomal;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolDemo {
	
	final static String HOST = "localhost";
	final static int PORT = 6379;
	final static String PASSWORD = "as1df@#$3%Mk4Io9Ps";
	
	final static int MAX_ACTIVE = 10;
	final static int MAX_IDLE = 100;
	final static int MAX_WAIT = 3000; 
	final static int TIMEOUT = 10000;   	//超时时间
	
	//单例模式
    static JedisPool jedisPool = null;  
	
	public static JedisPool initJedisPool(){
		if (jedisPool==null) {
			 JedisPoolConfig config = new JedisPoolConfig();  
	         config.setMaxTotal(MAX_ACTIVE);  
	         config.setMaxIdle(MAX_IDLE);  
	         config.setMaxWaitMillis(MAX_WAIT);  
	         jedisPool = new JedisPool(config, HOST, PORT, TIMEOUT); 
		}
		return jedisPool;
	}
	
	public synchronized Jedis getJedis() {
		initJedisPool();
		return jedisPool.getResource();
	}
	
}
