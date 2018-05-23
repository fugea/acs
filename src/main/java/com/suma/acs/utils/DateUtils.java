/***********************************************************************
 * $Id: DateUtils.java,v 1.1 Jun 14, 2008 4:37:56 PM Victor Exp $
 * 
 * @author: Victor
 * 
 * (c)Copyright 2011 Topoview All rights reserved.
 ***********************************************************************/
package com.suma.acs.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author Victor
 * @created @2011-9-26-下午03:02:31 comment by @bravin:
 *          SimpleDateFormat不是线程安全的，所有线程共有一个实例不安全，建议采用DateUtils.getDateFormat()
 *          得到实例，只支持 yyyy-MM-dd HH:mm:ss
 * 
 */
public class DateUtils {

	/**
	 * 如果这里不设置为 1000L而是 1000的话，大数将装不下：by @Bravin
	 */
	public static final long MINUTE_MILLS = 60 * 1000L;

	public static final long HOUR_MILLS = 60 * MINUTE_MILLS;

	public static final long DAY_MILLS = 24 * HOUR_MILLS;

	public static final long WEEK_MILLS = 7 * DAY_MILLS;

	public static final long WEEKDAY_MILLS = 5 * DAY_MILLS;

	public static final long MONTH_MILLS = 30 * DAY_MILLS;

	public static final long YEAR_MILLS = 365 * DAY_MILLS;

	public static final SimpleDateFormat FILENAME_FORMAT = new SimpleDateFormat("yyMMdd");

	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd");

	public static final SimpleDateFormat MINUTE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	public static final SimpleDateFormat FULL_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static final SimpleDateFormat FULL_S_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

	public static final SimpleDateFormat FULL_Z_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S,Z");

	public static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");

	public static final long SECOND_OBSCURE = 60 * 1000L - 1;

	public static final long MINUTE_OBSCURE = 60 * MINUTE_MILLS - 1;

	public static final long HOUR_OBSCURE = 24 * HOUR_MILLS - 1;

	public static final long DAY_OBSCURE = 7 * DAY_MILLS - 1;

	public static final long WEEK_OBSCURE = 4 * WEEK_MILLS - 1;

	public static final long MONTH_OBSCURE = 12 * MONTH_MILLS - 1;

	public static String buildFileNameByTime() {
		return FILENAME_FORMAT.format(new Date());
	}

	public static String format(Date d) {
		return d == null ? "" : FULL_FORMAT.format(d);
	}

	public static String format(long l) {
		return FULL_FORMAT.format(new Date(l));
	}

	public static String getCurrentTime() {
		return FULL_FORMAT.format(new Date());
	}

	public static String dateFormat(Date date) { return DATE_FORMAT.format(date); }

	public static String timeFormat(Date date) { return TIME_FORMAT.format(date); }

	/**
	 * 适用ThreadLocal来为每个线程创建一个实例，以保证线程安全
	 */
	private static ThreadLocal<?> threadLocal = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected synchronized SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
	};

	/**
	 * 得到 SimpleDateFormat 实例
	 * 
	 * @return
	 */
	public static DateFormat getDateFormat() {
		return (DateFormat) threadLocal.get();
	}

	/**
	 * 解析一个日期字符串为日期
	 * 
	 * @param text
	 * @return
	 * @throws ParseException
	 */
	public static Date parse(String text) throws ParseException {
		return getDateFormat().parse(text);
	}

	public static Date parse(String text, String format) {
	   SimpleDateFormat fo = new SimpleDateFormat(format);
        try {
            return fo.parse(text);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

	/**
	 * 解析一个日期字符串为长正整形
	 * 
	 * @param text
	 * @return
	 * @throws ParseException
	 */
	public static Long parseLong(String text) throws ParseException {
		return parse(text).getTime();
	}

	/**
	 * 解析一个日期字符串为数据库适用的Timestamp类型
	 * 
	 * @param text
	 * @return
	 * @throws ParseException
	 */
	public static Timestamp parseToTimestamp(String text) throws ParseException {
		return Timestamp.valueOf(text);
		// return new Timestamp(parseLong(text));
	}

	/**
	 * 提供通用方法，方便统一管理，也方便以后版本升级后更改为不同的实现
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Timestamp parseToTimestamp(Date date) throws ParseException {
		return new Timestamp(date.getTime());
	}

	/**
	 * java.sql.Timestamp的toString方法会留下 ms级别的小数位，所以使用这个
	 * 
	 * @param timestamp
	 * @return
	 * @throws ParseException
	 */
	public static String parseToString(Timestamp timestamp) throws ParseException {
		return getDateFormat().format(timestamp);
	}

	/**
	 * 得到当前的Timestamp
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static Timestamp getCurrentTimestamp() throws ParseException {
		return parseToTimestamp(new Date());
	}
}
