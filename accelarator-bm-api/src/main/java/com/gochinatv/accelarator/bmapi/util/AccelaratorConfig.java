package com.gochinatv.accelarator.bmapi.util;

public final class AccelaratorConfig {
	
	
	public static final int API_STATUS_ERROR = 1;//API ok status 0, error status 1

	
	public static final int DEVICE_ONE_SCREEN = 1;//屏幕数为1
			
	public static final int DEVICE_FOUR_SCREEN = 4;//屏幕数为4
	
	
	public static final String DEVICE_DEFAULT_MAC = PropertiesUtil.getInstance().getProperty("device.default.mac");
	public static final String DEVICE_DEFAULT_TIME = PropertiesUtil.getInstance().getProperty("device.default.time");
	
	public static final int DEVICE_SCREEN_INTERVAL = Integer.parseInt(PropertiesUtil.getInstance().getProperty("device.screen.shotInterval"));
	public static final int DEVICE_SCREEN_SHOTIMGW= Integer.parseInt(PropertiesUtil.getInstance().getProperty("device.screen.shotImgW"));
	public static final int DEVICE_SCREEN_SHOTIMGH= Integer.parseInt(PropertiesUtil.getInstance().getProperty("device.screen.shotImgH"));

	
	public static final int DEVICE_SCREEN1_ADTYPE= Integer.parseInt(PropertiesUtil.getInstance().getProperty("device.layout.screen1.adType"));
	public static final String DEVICE_SCREEN1_ADWIDTH= PropertiesUtil.getInstance().getProperty("device.layout.screen1.adWidth");
	public static final String DEVICE_SCREEN1_ADHEIGHT = PropertiesUtil.getInstance().getProperty("device.layout.screen1.adHeight");
	public static final String DEVICE_SCREEN1_ADTOP= PropertiesUtil.getInstance().getProperty("device.layout.screen1.adTop");
	public static final String DEVICE_SCREEN1_ADLEFT= PropertiesUtil.getInstance().getProperty("device.layout.screen1.adLeft");
	
	public static final int DEVICE_SCREEN2_ADTYPE= Integer.parseInt(PropertiesUtil.getInstance().getProperty("device.layout.screen2.adType"));
	public static final String DEVICE_SCREEN2_ADWIDTH= PropertiesUtil.getInstance().getProperty("device.layout.screen2.adWidth");
	public static final String DEVICE_SCREEN2_ADHEIGHT = PropertiesUtil.getInstance().getProperty("device.layout.screen2.adHeight");
	public static final String DEVICE_SCREEN2_ADTOP= PropertiesUtil.getInstance().getProperty("device.layout.screen2.adTop");
	public static final String DEVICE_SCREEN2_ADLEFT= PropertiesUtil.getInstance().getProperty("device.layout.screen2.adLeft");
	
	public static final int DEVICE_SCREEN3_ADTYPE= Integer.parseInt(PropertiesUtil.getInstance().getProperty("device.layout.screen3.adType"));
	public static final String DEVICE_SCREEN3_ADWIDTH= PropertiesUtil.getInstance().getProperty("device.layout.screen3.adWidth");
	public static final String DEVICE_SCREEN3_ADHEIGHT = PropertiesUtil.getInstance().getProperty("device.layout.screen3.adHeight");
	public static final String DEVICE_SCREEN3_ADTOP= PropertiesUtil.getInstance().getProperty("device.layout.screen3.adTop");
	public static final String DEVICE_SCREEN3_ADLEFT= PropertiesUtil.getInstance().getProperty("device.layout.screen3.adLeft");
	
	public static final int DEVICE_SCREEN4_ADTYPE= Integer.parseInt(PropertiesUtil.getInstance().getProperty("device.layout.screen4.adType"));
	public static final String DEVICE_SCREEN4_ADWIDTH= PropertiesUtil.getInstance().getProperty("device.layout.screen4.adWidth");
	public static final String DEVICE_SCREEN4_ADHEIGHT = PropertiesUtil.getInstance().getProperty("device.layout.screen4.adHeight");
	public static final String DEVICE_SCREEN4_ADTOP= PropertiesUtil.getInstance().getProperty("device.layout.screen4.adTop");
	public static final String DEVICE_SCREEN4_ADLEFT= PropertiesUtil.getInstance().getProperty("device.layout.screen4.adLeft");


}
