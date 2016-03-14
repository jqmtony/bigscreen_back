package com.gochinatv.accelarator.framework.web.base.controller;


import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class BaseController {
   
	protected HttpServletRequest getRequest() {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		return ((ServletRequestAttributes)ra).getRequest();
	}
	
	protected HttpSession getSession() {
		return getRequest().getSession();
	}
	
	public Map<String,Object> success(Object data){
		Map<String,Object> datas=new HashMap<String, Object>();
		datas.put("status","success");
		datas.put("msg","操作成功！");
		datas.put("code","200");
		datas.put("data",data);
		return datas;
	}
	
	public Map<String,Object> error(){
		Map<String,Object> data=new HashMap<String, Object>();
		data.put("status","failure");
		data.put("msg","操作失败！");
		data.put("code","400");
		return data;
	}
	
	public Map<String,Object> error(Object data){
		Map<String,Object> datas=new HashMap<String, Object>();
		datas.put("status","failure");
		datas.put("msg","操作失败！");
		datas.put("code","400");
		datas.put("data","操作失败:"+data);
		return datas;
	}
	
}
