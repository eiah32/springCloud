package com.eiah.iframework.util;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import com.eiah.util.DataTimeUtil;
import com.eiah.util.DataTimeUtil.TimeFormat;

/**
 * @ClassName FileUploadUtil
 * @Package com.eiah.iframework.util
 * @Description 文件上传工具类
 * @auther eiah32
 * @datatime 2017年9月25日下午10:45:54
 */
public class FileUploadUtil {
	public static final List<String> IMG_TYPES = Arrays.asList("image/jpg", "image/jpeg", "image/png", "image/gif");

	/**
	 * @Title rename
	 * @Description 文件重命名
	 * @param fileName
	 *            文件名
	 * @return String 文件名
	 * @auther eiah32
	 * @datatime 2017年9月27日下午10:28:11
	 */
	public static String rename(String fileName) {
		return DataTimeUtil.getCurrentDateTime(TimeFormat.LONG_DATE_PATTERN_WITH_MILSEC_NULL)
				+ IDGenerator.getFixLenthString(6) + "_src" + fileName.substring(fileName.lastIndexOf("."));
	}

	/**
	 * 
	 * @title allowUpload
	 * @description 校验文件类型是否是被允许的
	 * @param postfix
	 * @return boolean
	 * @auther eiah32
	 * @datatime 2017年9月25日下午10:39:43
	 */
	public static boolean allowUpload(String postfix) {
		return IMG_TYPES.contains(postfix);
	}

	/*
	 * int hashCode = uploadFileName.hashCode(); 1101 1010 1000 1001 0100 1000
	 * 0000 0000 0000 0000 0000 1111 &0xf -------------------------------------
	 * 0000 0000 0000 0000 0000 1000 取最后四位：0000~1111:0~15共16个
	 * 
	 * 1101 1010 1000 1001 0100 1000 0000 0000 0000 0000 1111 0000 &0xf0
	 * ------------------------------------ 0000 0000 0000 0000 0100 0000 >>4
	 * 0000 0000 0000 0000 0000 0100 最后四位：0000~1111:0~15共16个 创建新的存放目录：/8/4/
	 */
	/**
	 * @Title createStoreDirectory
	 * @Description 根据父目录创建子目录
	 * @param parentDirectory
	 *            父目录
	 * @param uploadFileName
	 *            文件名
	 * @return String 子目录
	 * @auther eiah
	 * @datatime 2017年9月25日下午10:52:28
	 */
	public static String createStoreDirectory(String parentDirectory, String uploadFileName) {
		int hashCode = uploadFileName.hashCode();
		int dir1 = hashCode & 0xf;
		int dir2 = (hashCode & 0xf0) >> 4;
		String directory = "" + dir1 + File.separator + dir2;
		File file = new File(parentDirectory, directory);
		if (!file.exists()) {
			file.mkdirs();
		}
		return directory;
	}

}
