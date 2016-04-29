package com.gochinatv.accelarator.framework.web.base.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
   
	public final static String YYYY_MM_DD = "yyyy-MM-dd"; 
	public final static String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS"; 
	public final static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	
	
	public static SimpleDateFormat SDF_YYYY_MM_DD = new SimpleDateFormat(YYYY_MM_DD);
	public static SimpleDateFormat SDF_YYYY_MM_DD_HH_MM_SS = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
	
	
	/**
	 * 两个日期之间的时间段间隔  （天数）
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws ParseException 
	 */
	public static long getBetweenDays(String startTime,String endTime) throws Exception{
		Calendar cal = Calendar.getInstance();
        cal.setTime(SDF_YYYY_MM_DD.parse(startTime));    
        long start_millis = cal.getTimeInMillis();   
        
        cal.setTime(SDF_YYYY_MM_DD.parse(endTime));   
        long end_millis = cal.getTimeInMillis();
        
        long between=(end_millis-start_millis)/(1000*3600*24);
        return between;
	}
	
	
	/**
	 * 当前时间基础上加上天
	 * @param amount
	 * @return
	 * @throws Exception
	 */
	public static String addDay(int amount) throws Exception{
		Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR,amount);        
        return SDF_YYYY_MM_DD.format(cal.getTime());
	}
	
	
	/**
	 * 时间基础上添加秒数
	 * @param amount
	 * @return
	 * @throws Exception
	 */
	public static String addSecond(String date,int amount) throws Exception{
		Calendar cal = Calendar.getInstance();
		cal.setTime(SDF_YYYY_MM_DD_HH_MM_SS.parse(date));
        cal.add(Calendar.SECOND,amount);    
        return SDF_YYYY_MM_DD_HH_MM_SS.format(cal.getTime());
	}
	
	
	/**
	 * 格式化日期时间格式为：yyyy-MM-dd hh:mm:ss
	 * @param date
	 * @return
	 * @throws ParseException 
	 */
	public static Date formatDate(String date) throws ParseException{
		return SDF_YYYY_MM_DD_HH_MM_SS.parse(date);
	}
	
	
}
