package com.cbry.senior;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.cbry.nomal.JedisPoolDemo;

import redis.clients.jedis.Jedis;

public class RedisLock {

	private static final String LOCK_TITLE = "redisLock_wallet";
	private static final String KEY = "wallet";
	private static final int THREAD_NUM = 100;
	public static void main(String[] args) {
		Jedis jedis = JedisPoolDemo.initJedisPool().getResource();

		jedis.set(KEY, "100");
		jedis.del(LOCK_TITLE);

		Executor threadExecutor = Executors.newFixedThreadPool(60);
		for (int i = 0; i < THREAD_NUM; i++) {
			threadExecutor.execute(new WalletConsumer());
		}
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("最终钱包还有：" + jedis.get(KEY));
	}

	
	
	//消费线程
	static class WalletConsumer implements Runnable {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			// 线程私有
			Jedis jedis = JedisPoolDemo.getJedis();

			//一直请求获取锁，直到获取锁		
			while (true) {
				if (jedis.setnx(LOCK_TITLE, "locked") == 1) { // 未上锁就上锁
					jedis.expire(LOCK_TITLE, 6);
					
					int wallet =Integer.valueOf(jedis.get(KEY)) - 1;
					jedis.set(KEY, String.valueOf(wallet));
					System.out.println(Thread.currentThread().getName() + "消费了钱包" + wallet);
					break;
				}else {
				//	System.err.println("被锁住");
				}
			}

			//释放锁
			jedis.close();
			if (Integer.valueOf(jedis.get(KEY)) != 6) {
				jedis.del(LOCK_TITLE);
			}

		}
	}

}
