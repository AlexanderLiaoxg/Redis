package com.cbry.config;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		//注册拦截器（嵌入自定义拦截逻辑代码）
		InterceptorRegistration registration = registry.addInterceptor(new Filter());
		System.err.println("注入；拦截器实例");
		//加入拦截配置方法：不拦截的路径
		System.err.println("添加放行路径");
		registration.excludePathPatterns("/filter");
		//registration.excludePathPatterns();
		//registration.excludePathPatterns("/*");
	}

}
