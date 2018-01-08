package com.eiah.util;

import java.math.BigInteger;
import java.util.Random;

public class MyUtil {
	/**
	 * 返回一个String类型的主键
	 * @return PK
	 */
	public static String getPK() {
		return new BigInteger(165, new Random()).toString(36).toUpperCase();
	}
	    
}
