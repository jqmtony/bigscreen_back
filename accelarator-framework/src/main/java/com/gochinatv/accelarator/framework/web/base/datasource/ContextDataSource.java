package com.gochinatv.accelarator.framework.web.base.datasource;

public class ContextDataSource {

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	public static final String MASTER = "master";

	public static final String SLAVE = "slave";

	public static void setDataSourceType(String dataSource) {
		contextHolder.set(dataSource);
	}

	public static String getDataSource() {
		return contextHolder.get();
	}

	public static void clearDataSource() {
		contextHolder.remove();
	}

	public static void setMaster() {
		setDataSourceType(MASTER);
	}

	public static void setSlave() {
		setDataSourceType(SLAVE);
	}
    
}
