package com.gochinatv.accelarator.bmapi.controller;


import java.util.HashMap;
import java.util.Map;


public class BaseController{
   
	
	public Map<String,Object> success(Object data){
		Map<String,Object> datas=new HashMap<String, Object>();
		datas.put("status",true);
		datas.put("msg","操作成功！");
		datas.put("code","200");
		datas.put("data",data);
		return datas;
	}
	
	public Map<String,Object> error(){
		Map<String,Object> data=new HashMap<String, Object>();
		data.put("status",false);
		data.put("msg","操作失败！");
		data.put("code","400");
		return data;
	}
	
	public Map<String,Object> error(Object data){
		Map<String,Object> datas=new HashMap<String, Object>();
		datas.put("status",false);
		datas.put("msg",data);
		datas.put("code","400");
		datas.put("data","操作失败:"+data);
		return datas;
	}
	
}
