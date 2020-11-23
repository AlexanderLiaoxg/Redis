package com.cbry.nomal;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.Transaction;

public class RedisTransaction {

	public static void main(String[] args) {
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		// 开启事务
		Transaction multi = jedis.multi();

		try {
			multi.set("transaction1", "hello,redis transaction1!");
			multi.set("transaction2", "hello,redis transaction2! :)");
			
			//执行事务
			System.err.println(multi.exec().toString());
		} catch (Exception e) {
			// TODO: handle exception
			multi.discard();	//放弃事务
			e.printStackTrace();
		}finally {
			System.out.println("Transaction1：" + jedis.get("transaction1"));
			System.out.println("Transaction2：" + jedis.get("transaction2"));
			jedis.close();	//关闭连接
		}
	} 
}
