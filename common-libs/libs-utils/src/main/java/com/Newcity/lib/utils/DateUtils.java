package com.Newcity.lib.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class DateUtils {
	
	//当前时间
    public static Timestamp getNowTime(){
        return new Timestamp(System.currentTimeMillis());
    }
	
	// 取得一个自定义事件
	public static String getCurrentDate( String dataFormat ){
		SimpleDateFormat format = new SimpleDateFormat( dataFormat );
		return format.format( new Date() );
	}
 
	
}
