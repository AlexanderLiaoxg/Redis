package com.cbry.nomal;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

public class JedisPipelineDemo {
	
	Pipeline getJedisPipeline() {		
		Jedis jedis = new Jedis("localhost",6379);
		Pipeline pipeline = jedis.pipelined();
		pipeline.set("d", "w");pipeline.set("d", "w");pipeline.set("d", "w");pipeline.set("d", "w");pipeline.set("d", "w");
		List<Object> list = pipeline.syncAndReturnAll();
		return pipeline;
	}
}
