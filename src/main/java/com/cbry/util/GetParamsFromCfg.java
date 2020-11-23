package com.cbry.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;
import org.ini4j.Profile.Section;

public class GetParamsFromCfg {
	
	private void onlyProperties() throws IOException {
		Properties prop = new Properties();
		InputStream is = null;
		
		is = GetParamsFromCfg.class.getClassLoader().getResourceAsStream("redisProperties.cfg");	
		prop.load(is);	
		System.err.println(prop.get("sayHello"));
		System.err.println(prop.get("integer"));
		is.close();
	}
	
	private void withIni4j(){
		
		Ini ini = null;
		try {
			ini = new Ini(Thread.currentThread().getContextClassLoader().getResourceAsStream("redisProperties.cfg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("读取文件错误");
		}
		
		//获取ini头
		Section section = ini.get("Redis");
		
		System.err.println(section.get("redisNodes"));
		System.err.println(section.get("password"));
		
	}
	
public static void main(String[] args) throws IOException {
	new GetParamsFromCfg().withIni4j();
}
}
