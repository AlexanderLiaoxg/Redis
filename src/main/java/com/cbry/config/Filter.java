package com.cbry.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class Filter implements HandlerInterceptor{

    /**
     * 在请求处理之前进行调用（进入Controller之前）
     */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.err.println("拦截判断");
		String userName = (String) request.getSession().getAttribute("userName");
		if (userName==null) {
			userName = "";
		}
		
		//null不能进行equals判断会抛出空指针错误
		if (userName.equals("cbry")) {
			return true;
		}else {
			return true;
		}
		
		
	}

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller调用之后）
     */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

    /**
     * 在整个请求结束之后被调用，也就是渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

}
