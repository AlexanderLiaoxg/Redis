package com.cbry.primary;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

public class JedisDemo {
	
	final String host = "172.18.194.90";
	final int port = 7001;
	final String password = "as1df@#$3%Mk4Io9Ps";

	private void singleRedis() {
		Jedis jedis = new Jedis(host, port);
		jedis.auth(password);
		// 如果redis是集群模式会报错：JedisMovedDataException , 需要采用jedisCluster来进行连接
		jedis.set("first", "Hello single Jedis, finish study-work of Redis in one day");
		System.err.println(jedis.get("first"));
	}

	final int REDIS_COMMAND_TIMEOUT = 5000;
	
	private void clusterRedis() {
		HostAndPort hostAndPort = new HostAndPort(host, port);
		Set<HostAndPort> hostAndPortSet = new HashSet<>();
		hostAndPortSet.add(hostAndPort);
		
		//10是maxAttempts , 第一个参数可以为hostAndPort或者Set
		JedisCluster jedisCluster = new JedisCluster(hostAndPortSet, REDIS_COMMAND_TIMEOUT, REDIS_COMMAND_TIMEOUT, 10, password, this.getGenericObjectPoolConfig());
		jedisCluster.set("test", "Hello cluster Jedis, finish study-work of Redis in one day");
		System.err.println(jedisCluster.get("test"));
	}
	
	private GenericObjectPoolConfig getGenericObjectPoolConfig() {
		GenericObjectPoolConfig genericObjectPool = new GenericObjectPoolConfig();
		genericObjectPool.setMaxIdle(10);
		genericObjectPool.setMaxTotal(100);
		genericObjectPool.setMinEvictableIdleTimeMillis(30000); // 逐出连接的最小空闲时间 30s
		genericObjectPool.setSoftMinEvictableIdleTimeMillis(60000); // 空闲逐出时间1分钟
		return genericObjectPool;
	}

	public static void main(String[] args) {
		JedisDemo jedisDemo = new JedisDemo();
		//jedisDemo.singleRedis();
		jedisDemo.clusterRedis();
	}
	
	JedisCluster getJedisCluster(){
		
		HostAndPort hostAndPort = new HostAndPort(host, port);
		Set<HostAndPort> hostAndPortSet = new HashSet<>();
		hostAndPortSet.add(hostAndPort);
		JedisCluster jedisCluster = new JedisCluster(hostAndPortSet, REDIS_COMMAND_TIMEOUT, REDIS_COMMAND_TIMEOUT, 10, password, this.getGenericObjectPoolConfig());
		return jedisCluster;
		
	}
}
