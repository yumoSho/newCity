package com.Newcity.libs.filter;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by dev on 2017/4/20.
 */
public interface WebToken {
    Map<String,Object> getToken(Map<String, String> map, HttpServletResponse response);
    boolean isExist(String token);
    boolean isLogin(String token);
    Long refresh(String token);
    boolean loginOut(String token);
    String getAccountId(String token);
    String getAccountNo(String token);
    String isAccountIp(String token);
}
