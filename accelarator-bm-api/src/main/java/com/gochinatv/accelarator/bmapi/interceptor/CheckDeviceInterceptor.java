package com.gochinatv.accelarator.bmapi.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.gochinatv.accelarator.bmapi.bean.Business;
import com.gochinatv.accelarator.bmapi.util.redis.RedisUtil;


public class CheckDeviceInterceptor extends HandlerInterceptorAdapter {
	Log logger = LogFactory.getLog(CheckDeviceInterceptor.class);
	
	
	/**
	 * 验证登陆拦截器
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HandlerMethod method = (HandlerMethod)handler;
		CheckDeviceInterceptorAnnotation annotation = method.getMethodAnnotation(CheckDeviceInterceptorAnnotation.class);
		if(annotation!=null){
			String msg="不是有效操作";
			response.setContentType("text/html;charset=UTF-8");
			//从 request header获取 token
			//如果无 则不通过，如果有并且redis也有通过，否则不通过
			String businessToken  = request.getHeader("businessToken");
			String accessToken  = request.getHeader("accessToken");
			if(StringUtils.isNotBlank(businessToken)){
				Business business = (Business)RedisUtil.get(accessToken);
				if(StringUtils.equals(businessToken, ""+business.getId())){
					return true;
				}
				response.getWriter().write("{\"status\":"+false+",\"msg\":\""+msg+"\"}");
				return false;
			}else{
				logger.error(msg);
				response.getWriter().write("{\"status\":"+false+",\"msg\":\""+msg+"\"}");
				return false;
			}
		}
		return true;
	}
}
