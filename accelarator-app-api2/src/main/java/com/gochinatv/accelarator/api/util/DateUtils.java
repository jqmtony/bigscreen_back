package com.gochinatv.accelarator.api.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author 奇
 * 
 *         2014-12-29
 */
public class DateUtils {

	private static Logger logger = LoggerFactory.getLogger(DateUtils.class);

	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_MIN_FORMAT = "yyyy-MM-dd HH:mm";
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String TIME_FORMAT = "HH:mm:ss";

	private DateUtils() {
	}

	private static final String[] FORMATS = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm",
			"yyyy-MM-dd HH:mm:ss", "HH:mm", "HH:mm:ss", "yyyy-MM", "yyyyMMdd" };

	public static Date convert(String str) {
		if (str != null && str.length() > 0) {
			if (str.length() > 10 && str.charAt(10) == 'T')
				str = str.replace('T', ' '); // ȥ��json-lib�ӵ�T��ĸ
			for (String format : FORMATS) {
				if (str.length() == format.length()) {
					try {
						if (logger.isDebugEnabled())
							logger.debug("convert " + str + " to date format "
									+ format);

						Date date = new SimpleDateFormat(format).parse(str);

						if (logger.isDebugEnabled())
							logger.debug("****" + date + "****");

						return date;
					} catch (ParseException e) {
						logger.warn(e.getMessage());
					}
				}
			}
		}
		return null;
	}

	public static Date convert(String str, String format) {
		if (!StringUtils.isEmpty(str)) {
			try {
				Date date = new SimpleDateFormat(format).parse(str);
				return date;
			} catch (ParseException e) {
				logger.warn(e.getMessage());
			}
		}
		return null;
	}

	public static String convert(Date date) {
		return convert(date, DATE_TIME_FORMAT);
	}

	public static String convert(Date date, String dateFormat) {
		if (date == null)
			return null;

		if (null == dateFormat)
			dateFormat = DATE_TIME_FORMAT;

		return new SimpleDateFormat(dateFormat).format(date);
	}

	public static String formate(Object date) {
		if (date == null) {
			return null;
		}
		return new SimpleDateFormat(DATE_TIME_FORMAT).format(date);
	}

	public static Date getYesterdayStart() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	public static Date getYesterdayEnd() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE));
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}
	/**
	 * 返回一个指定日期的下一天
	 * 
	 * @param date
	 *            初始日期
	 * @return 初始日期加一天后的日期
	 */
	public static Date getNextDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}
	public static void main(String[] args) {
		String now = DateUtils.convert(getNextDate(new Date()), DateUtils.DATE_FORMAT);
		System.out.println(getNextDate(new Date()));
	}
}
