package com.gochinatv.accelarator.bmapi.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 属性文档
 * @author LBQ-PC
 *
 */
public class PropertiesUtil extends Properties {

	private static final long serialVersionUID = 1L;

	private PropertiesUtil(String filePath) {
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static class SingletonContainer {
		private static PropertiesUtil instance = new PropertiesUtil(
				"/mis.properties");
	}

	public static PropertiesUtil getInstance(String fileName) {
		return new PropertiesUtil(fileName);
	}

	public static PropertiesUtil getInstance() {
		return SingletonContainer.instance;
	}
}
