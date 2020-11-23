package com.cbry.subscribe;

import com.cbry.nomal.JedisPoolDemo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class Doit {

	public void toBePublisher() throws InterruptedException {
		String channel = "broadcast_channel";
		Jedis jedis = new Jedis("localhost");
		JedisPool jedisPool = JedisPoolDemo.initJedisPool();

		Publisher publisher = new Publisher(jedisPool);
		publisher.run();

		// jedis.publish(channel, "hello world , broadcast_channel");
	}

	public void toBeSubscriber() {
		String channel = "broadcast_channel";
		Jedis jedis = new Jedis("localhost");
		JedisPool jedisPool = JedisPoolDemo.initJedisPool();

		Subscriber subscriber = new Subscriber();
		jedis.subscribe(subscriber, channel);
	}

	public static void main(String[] args) throws InterruptedException {
		new Doit().toBeSubscriber();
	}
}
