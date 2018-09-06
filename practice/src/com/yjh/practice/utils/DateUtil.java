package com.yjh.practice.utils;

import java.sql.Date;
import java.util.Calendar;

/**
 * 
 * Description: 格式化时间 
 * @author YJH
 * @date 2018年6月1日  
 *
 */
public class DateUtil {
	//传入参数类似于:2015-12-30
	public static Date splitStringToDate(String dateString) {
		String[] str = dateString.split("-");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR,Integer.parseInt(str[0]));
		calendar.set(Calendar.MONTH,(Integer.parseInt(str[1])-1));
		calendar.set(Calendar.DAY_OF_MONTH,Integer.parseInt(str[2]));
		java.util.Date date1 = calendar.getTime();
		java.sql.Date sqlDate=new java.sql.Date(date1.getTime());
		return sqlDate;
	}
}
