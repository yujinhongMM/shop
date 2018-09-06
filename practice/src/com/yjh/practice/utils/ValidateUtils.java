
package com.yjh.practice.utils;
/**
 * 
 * Description 防止SQL注入工具类
 * @author YJH
 * @date 2018年6月2日  
 *
 */
public class ValidateUtils {
	/**
	 * <p>Title: validate</p>
	 * <p>Description: 防止SQL注入</p>
	 * @param str 要过滤的字符串，一般为页面获取的参数值
	 * @return 如果有非法字符，则返回true,没有非法字符，则显示false
	 */
	public static boolean validate(String pageParameter) {
		boolean flag = false ;
		String inj_str = "|insert|select|delete|update|drop|alter|count|"
				+ "declare|or";
		String pageParameter2 = pageParameter.toLowerCase();
		String inj_stra[] = inj_str.split("\\|"); 
		for(String i : inj_stra) {
			if (pageParameter2.indexOf(i) > 0) {
				flag = true ;
			} 
		}
		return flag ;
	}
}
