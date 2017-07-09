package com.Newcity.libs.filter.utils;

import com.Newcity.lib.utils.StrUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by dev on 2017/4/6.
 */
public class TokenUtils {
    private static final String CODE = "0123456789abcdefghijklmnopqrstuvwxyz";
    private static final int LENGTH = 20;
    public static final String TOKEN = "token-";
    public static final String WEBTOKEN = "webToken-";
    public static final String ACCOUNT = "account-";


    public static String getToken() {
        StringBuilder sb = new StringBuilder(LENGTH);
        for (int i = 0; i < LENGTH; i++) {
            int index = ThreadLocalRandom.current().nextInt(CODE.length());
            sb.append(CODE.charAt(index));
        }
        return sb.toString();
    }

    public static String buildTokenKey(String token){
        return TOKEN+token;
    }
    public static String buildWebTokenKey(String token,String accountNo){
        return WEBTOKEN+token+"-"+accountNo;
    }

    public static String tokenKey2Toekn(String token){
        return token.substring(TOKEN.length());
    }

    public static String buildAccountKey(String accountId){
        return ACCOUNT+accountId;
    }

    public static String getValueInRequest(HttpServletRequest request, String name){
        String value = request.getHeader(name);
        if(StrUtil.isNull(value)){
            Cookie[] cookie = request.getCookies();
            if(cookie != null){
                for (int i = 0; i < cookie.length; i++) {
                    Cookie cook = cookie[i];
                    if(cook.getName().equalsIgnoreCase(name)){ //获取键
                        value = cook.getValue().toLowerCase();//获取值
                    }
                }
            }
        }
        return value;
    }

    public static Cookie newCookie(String name,String value,Integer expire){
        Cookie cookie = new Cookie(name,value);
        cookie.setMaxAge(expire);
        return cookie;
    }
}
