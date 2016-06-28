package com.jeecms.common.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {

	/**
	 * 是否是今天。根据System.currentTimeMillis() / 1000 / 60 / 60 / 24计算。
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isToday(Date date) {
		long day = date.getTime() / 1000 / 60 / 60 / 24;
		long currentDay = System.currentTimeMillis() / 1000 / 60 / 60 / 24;
		return day == currentDay;
	}

	/**
	 * 
	 * @param date
	 *            判断是否是本周，取出日期，依据日期取出该日所在周所有日期，在依据这些日期是否和本日相等
	 * @return
	 */
	public static boolean isThisWeek(Date date) {
		List<Date> dates = dateToWeek(date);
		Boolean flag = false;
		for (Date d : dates) {
			if (isToday(d)) {
				flag = true;
				break;
			} else {
				continue;
			}
		}
		return flag;
	}

	/**
	 * 
	 * @param date
	 *            判断是否是本月的日期
	 * @return
	 */
	public static boolean isThisMonth(Date date) {
		long year = date.getYear();
		long month = date.getMonth();
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime().getYear() == year
				&& calendar.getTime().getMonth() == month;
	}

	/**
	 * 
	 * @param date
	 *            判断是否是本年的日期
	 * @return
	 */
	public static boolean isThisYear(Date date) {
		long year = date.getYear();
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime().getYear() == year;
	}

	/**
	 * 
	 * @param mdate
	 *            取出改日的一周所有日期
	 * @return
	 */
	public static List<Date> dateToWeek(Date mdate) {
		int day = mdate.getDay();
		Date fdate;
		List<Date> list = new ArrayList();
		Long fTime = mdate.getTime() - day * 24 * 3600000;
		for (int i = 0; i < 7; i++) {
			fdate = new Date();
			fdate.setTime(fTime + (i * 24 * 3600000));
			list.add(i, fdate);
		}
		return list;
	}

	public static Double diffTwoDate(Date begin, Date end) {
		if(begin==null||end==null){
			return 0.0;
		}else{
			return (end.getTime() - begin.getTime()) / 1000 / 60.0;
		}
		
	}

	public static String parseDate(Date date, String format) {
		SimpleDateFormat formater = new SimpleDateFormat(format);
		String dateString;
		dateString = formater.format(date);
		return dateString;
	}

	public static Date afterDate(Date date, Integer after) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + after);
		return calendar.getTime();
	}

	public static void main(String arg[]) {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 1);
		Double d=1/3.0;
		System.out.println(((double)Math.round(d*100))/100);
		System.out.println((double)Math.round(d*100)/100);
		System.out.println(35/100.0);
		System.out.println(afterDate(new Date(), 1));
	}
	
	public static long MILLIS_IN_DAY=86400000;
}
