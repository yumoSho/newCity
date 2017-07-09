package com.Newcity.lib.utils;

public class StrUtil {
	
	public static boolean isNull( String str){
		if(str==null || str.trim().equals("")){
			return true;
		}
		return false;
	}
	

	/***************************************************************************
	 * 【Beta 2.0】 转型方法: String to int | String to String | String to Timestamp | String to long 
	 **************************************************************************/

	/**
	 * 【类型转化】取得一个非空的整数值，如果提供的数据为空或者为字母，则自动转换为0
	 * 
	 * @param data
	 *            要转换的数据
	 * @return
	 */
	public static int getInt(String data) {
		try {
			return Integer.parseInt(data); 
		} catch (Exception e) {
//			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 【类型转化】取得一个非空的整数值，如果提供的数据为空或者为字母，则自动转换为 returnValue
	 * 
	 * @param data
	 *            要转换的数据
	 * @param returnValue
	 * @return
	 */
	public static int getInt(String data, int returnValue) {
		try {
			return Integer.parseInt(data);
		} catch (Exception e) {
			return returnValue;
		}
	}
	
	
	/***************************************************************************
	 * 【Beta 2.0】 转型方法: String to int | String to String | String to Timestamp | String to long 
	 **************************************************************************/

	/**
	 * 【类型转化】取得一个非空的整数值，如果提供的数据为空或者为字母，则自动转换为0
	 * 
	 * @param data
	 *            要转换的数据
	 * @return
	 */
	public static long getLong(String data) {
		try {
			return Long.parseLong(data); 
		} catch (Exception e) {
//			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 【类型转化】取得一个非空的整数值，如果提供的数据为空或者为字母，则自动转换为 returnValue
	 * 
	 * @param data
	 *            要转换的数据
	 * @param returnValue
	 * @return
	 */
	public static long getLong(String data, long returnValue) {
		try {
			return Long.parseLong(data);
		} catch (Exception e) {
			return returnValue;
		}
	}
	
	
	
	public static short getShort(String data) {
		try {
			return Short.parseShort(data); 
		} catch (Exception e) {
//			e.printStackTrace();
			return 0;
		}
	}
	

}
