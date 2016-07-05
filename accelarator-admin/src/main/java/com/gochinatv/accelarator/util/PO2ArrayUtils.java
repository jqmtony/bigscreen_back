package com.gochinatv.accelarator.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("all")
public class PO2ArrayUtils {
   
	public static List<Object[]> po2Array(Object data,String[] fields) throws Exception{
		List<Object[]> datas = new ArrayList<Object[]>();
		if(data instanceof List){
			List<Object> list = (ArrayList<Object>)data;
			int size = fields.length;
			for (Object object : list) {
				Class clz = object.getClass();
				Object[] obj = new Object[size]; 
				for (int i = 0; i < fields.length; i++) {
					String field = fields[i];
					Method method = clz.getDeclaredMethod("get"+(field.substring(0,1).toUpperCase())+field.substring(1));
					obj[i] = method.invoke(object); 
				}
				datas.add(obj);
			}
		}
		return datas;
	}
}
