package com.gochinatv.accelarator.util;

import java.util.HashMap;
import java.util.Map;

public class GlobalUtils {
   
	public static int ADS_VIDEO_COUNT=10;//循环视频广告数量10个
	
	public static int ADS_PLAY_TIME=36000;//循环视频播放时长600分钟  36000秒
	
	public static int ADS_EACH_PLAY_TIME=3600;//每个视频循环视频播放时长60分钟  3600秒
	
	/**
	 * 行政区划对应的CODE -> NAME
	 */
	public static Map<String,String> AREA_CODE_NAME = new HashMap<String, String>();
}
