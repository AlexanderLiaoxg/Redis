package com.cbry.jwt;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cbry.nomal.JedisPoolDemo;

import redis.clients.jedis.Jedis;

public class JwtDemo {
	
	/*加密生产token*/
	private static String getToken(String account) {
		// TODO Auto-generated method stub
		
		Date now = new Date();
		
		//密钥
		String key = "cbry_blog";
		
		JWTCreator.Builder builder = JWT.create();
		
		return JWT.create()
				//签发人
				.withIssuer("cbry")
				//签发时间
				.withIssuedAt(now)
				//过期时间
				.withExpiresAt(new Date(now.getTime() + 60*60*1000))
				//加密的数据
				.withClaim("account", account)
				//加密的方式：这里选择Hmac256
				.sign(Algorithm.HMAC256(key));
	}
	
	/*解密token*/
	private static String decodeToken(String token) {
		// TODO Auto-generated method stub
		DecodedJWT decodedJWT = null;
		
		decodedJWT = JWT.decode(token);
		
		if (new Date().getTime() > decodedJWT.getExpiresAt().getTime()) {
			return "token已经过期";
		}else {
			return "token解密信息account：" + decodedJWT.getClaim("account").asString();
		}
	}
	
	public static void main(String[] args) {
		String token = getToken("cbry101");
		System.out.println(token);
		Jedis jedis = JedisPoolDemo.initJedisPool().getResource();
		jedis.setex("jwt", 3600, token);
		System.err.println("解密从redis获取的token信息：" + decodeToken(jedis.get("jwt")));
		
		/*接下来就是将token传到cookie里面了，本地每次访问带cookie里面的token*/
	}

}
