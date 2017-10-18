package com.Newcity.module.business.util;

/**
 * Created by Administrator on 2017/9/25.
 */
public class NumberUtil {

    public static String ById(Integer length, String number){
        length = length - number.length();
        String reuqestData = "";
        for(int i = 0; i<length;i++){
            reuqestData += "0";
        }
        reuqestData += number;
        return reuqestData;
    }
}
