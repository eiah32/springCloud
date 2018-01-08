package com.eiah.iframework.util;

import java.util.Random;

public class IDGenerator {
	
	/**
	 * @Title getFixLenthString
	 * @Description 生成长度为strLength的随机数，位数不足前补0
	 * @param strLength
	 * @return String
	 * @auther eiah32
	 * @datatime 2017年9月27日下午9:59:24
	 */
	public static String getFixLenthString(int strLength) {  
	    // 将获得的获得随机数转化为字符串  
	    String fixLenthString = String.valueOf((1 + new Random().nextDouble()) * Math.pow(10, strLength));  
	  
	    // 返回固定的长度的随机数  
	    return fixLenthString.substring(1, strLength + 1);  
	} 
}
