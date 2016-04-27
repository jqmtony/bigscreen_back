package com.gochinatv.accelarator.api.util;


/**
 * 
 * @author 奇
 * 
 *         2014-12-19
 */
public final class MISConfig {
	// YouTuBe 视频地址解析地址
	public static final String CDN_API_HOST= PropertiesUtil.getInstance()
			.getProperty("mis.cdn.api.host");
	//CDN系统给媒资的系统id 
	public static final String CDN_API_CUSTOMERID= PropertiesUtil.getInstance()
			.getProperty("mis.cdn.api.customerid");
	
	public static final String CDN_API_CALLBACK_URL= PropertiesUtil.getInstance()
			.getProperty("mis.cdn.api.callback.url");
}
