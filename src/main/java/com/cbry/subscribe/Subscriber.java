package com.cbry.subscribe;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/*
 监听到订阅模式接受到消息时的回调 (onPMessage)
监听到订阅频道接受到消息时的回调 (onMessage )
订阅频道时的回调( onSubscribe )
取消订阅频道时的回调( onUnsubscribe )
订阅频道模式时的回调 ( onPSubscribe )
取消订阅模式时的回调( onPUnsubscribe )
 * */

public class Subscriber extends JedisPubSub{

	@Override
	public void onMessage(String channel, String message) {
		// TODO Auto-generated method stub
		System.err.println("收到来自" + channel + "的新消息：" + message);
	}

	@Override
	public void onPMessage(String pattern, String channel, String message) {
		// TODO Auto-generated method stub
		System.err.println("收到来自" + channel + "的新消息：" + message);
	}

	@Override
	public void onSubscribe(String channel, int subscribedChannels) {
		// TODO Auto-generated method stub
		System.err.println("恭喜你订阅：" + channel + "成功！");
	}

	@Override
	public void onUnsubscribe(String channel, int subscribedChannels) {
		// TODO Auto-generated method stub
		super.onUnsubscribe(channel, subscribedChannels);
	}

	@Override
	public void onPUnsubscribe(String pattern, int subscribedChannels) {
		// TODO Auto-generated method stub
		super.onPUnsubscribe(pattern, subscribedChannels);
	}

	@Override
	public void onPSubscribe(String pattern, int subscribedChannels) {
		// TODO Auto-generated method stub
		super.onPSubscribe(pattern, subscribedChannels);
	}

}
