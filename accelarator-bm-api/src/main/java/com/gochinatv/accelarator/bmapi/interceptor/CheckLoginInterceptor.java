package com.gochinatv.accelarator.bmapi.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.gochinatv.accelarator.bmapi.util.redis.RedisUtil;


public class CheckLoginInterceptor extends HandlerInterceptorAdapter {
	Log logger = LogFactory.getLog(CheckLoginInterceptor.class);
	
	
	/**
	 * 验证登陆拦截器
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HandlerMethod method = (HandlerMethod)handler;
		CheckLoginInterceptorAnnotation annotation = method.getMethodAnnotation(CheckLoginInterceptorAnnotation.class);
		if(annotation!=null){
			response.setContentType("text/html;charset=UTF-8");
			//从 request header获取 token
			//如果无 则不通过，如果有并且redis也有通过，否则不通过
			String token  = request.getParameter("token");
			if(token != null || !"".equals(token)){
				if(!RedisUtil.notTTL(token)){
					logger.error("参数错误 ");
					response.getWriter().write("参数错误");
					return false;
				}
			}else{
				logger.error("参数为空");
				response.getWriter().write("参数为空");
				return false;
			}
		}
		return true;
	}
}
