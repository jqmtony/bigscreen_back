package com.gochinatv.accelarator.api.util;

/**
 * 
 * @author 奇
 * 
 *         2014-12-19
 */
public final class ConfigUtil {
	// 图上传地址
	public static final String IMAGE_PATH = PropertiesUtil.getInstance()
			.getProperty("mis.image.filepath");
	// 图请求地址
	public static final String IMAGE_URL = PropertiesUtil.getInstance()
			.getProperty("mis.image.url");
	
	

}
