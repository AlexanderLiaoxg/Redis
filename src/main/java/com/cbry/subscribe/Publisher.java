package com.cbry.subscribe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class Publisher implements Runnable {

	private final JedisPool jedisPool;

	public Publisher(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));System.err.println("is running");
        Jedis jedis = jedisPool.getResource();   //连接池中取出一个连接
        while (true) {
            String line = null;
            try {
                line = reader.readLine();
                if (!"quit".equals(line)) {
                	System.err.println("输入一次");
                    jedis.publish("broadcast_channel", line);   //从 mychannel 的频道上推送消息
                } else {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}

}
