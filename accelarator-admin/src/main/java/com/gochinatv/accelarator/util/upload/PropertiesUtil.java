package com.gochinatv.accelarator.util.upload;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil extends Properties {
	/**
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PropertiesUtil(String filePath){
		try {
			InputStream is= getClass().getResourceAsStream(filePath);
			load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static class SingletonContainer{
		private static PropertiesUtil instance = new PropertiesUtil("/conf.properties");
	}
    public static PropertiesUtil getInstance(String fileName){
        return   new PropertiesUtil("/conf.properties");
    }
	public static PropertiesUtil getInstance(){
		return SingletonContainer.instance;
	}
}
