package com.gochinatv.accelarator.framework.web.base.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtils {
   
	public final static String YYYY_MM_DD = "yyyy-MM-dd"; 
	public final static String YYYY_MM_DD_HH_MM_SS_SSS = "yyyyMMddHHmmssSSS"; 
	
	public static SimpleDateFormat SDF_YYYY_MM_DD = new SimpleDateFormat(YYYY_MM_DD);
	
	
	/**
	 * 两个日期之间的时间段间隔  （天数）
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws ParseException 
	 */
	public static long getBetweenDays(String startTime,String endTime) throws Exception{
		Calendar cal = Calendar.getInstance();
        cal.setTime(DateUtils.SDF_YYYY_MM_DD.parse(startTime));    
        long start_millis = cal.getTimeInMillis();   
        
        cal.setTime(DateUtils.SDF_YYYY_MM_DD.parse(endTime));   
        long end_millis = cal.getTimeInMillis();
        
        long between=(end_millis-start_millis)/(1000*3600*24);
        return between;
	}
}
