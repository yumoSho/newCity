package com.Newcity.libs.filter.impl;

import com.Newcity.libs.dmo.constant.TokenConstant;
import com.Newcity.libs.filter.WebToken;
import com.Newcity.libs.filter.utils.TokenUtils;
import com.Newcity.libs.redis.IRedis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dev on 2017/4/20.
 */
@Component
public class WebTokenImpl implements WebToken {

    private Logger logger = LoggerFactory.getLogger(WebTokenImpl.class);

    @Autowired
    private IRedis redis;
    @Value("${token.webAdmin.LoginToken}")
    private Integer LoginToken;

    /**
     * 获取token，
     * @param mapParams
     * accountRole 用户角色
     * accountState  用户状态
     * accountId  用户Id
     * accountIp  用户IP
     * accountNo 用户账号
     *
     * 将用户基本信息存在Token中 token格式：webToken-随机数-账号
     *    管理员修改子管理员状态是  需要清空Token
     * @return
     */
    public Map<String,Object> getToken(Map<String,String> mapParams, HttpServletResponse response){

        String accountRole =  mapParams.get("accountRole");
        String accountState =  mapParams.get("state");
        String accountId =  mapParams.get("accountID");
        String accountIp =  mapParams.get("accountIP");
        String accountNo =  mapParams.get("accountNo");
        if(StringUtils.isEmpty(accountRole) || StringUtils.isEmpty(accountState) || StringUtils.isEmpty(accountId) ){
            logger.info(" webAdmin getToken : ----------------------- params is null");
            return null;
        }

        String token = TokenUtils.getToken();

        try{
            //清空用户用户token
            String accountNoStr = TokenUtils.buildAccountKey(accountNo);
            String tokenStr = TokenUtils.buildTokenKey(token);
            String tokenOld = redis.get(accountNoStr);
            if(tokenOld !=  null){
                redis.del(accountNoStr);
                redis.del(tokenOld);
            }

            redis.set(accountNoStr,token);
            redis.hset(tokenStr,mapParams,LoginToken);
            //设置cookie返回给客户端
            TokenUtils.newCookie("token",token,LoginToken,response);
            Map<String,Object> mapResult = new HashMap<String,Object>();
            mapResult.put("token",token);
            mapResult.put("LoginToken",LoginToken);
            return mapResult;
        }catch(Exception e){
            logger.error("webAmind getToken : ------------" + e);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean isExist(String token){
        String tokenKey = TokenUtils.buildTokenKey(token);
        if(StringUtils.isEmpty(tokenKey)){
            return false;
        }
        return StringUtils.isEmpty(redis.hget(tokenKey,TokenConstant.KEY_TOKEN_STATE));
    }

    @Override
    public boolean isLogin(String token){
        String tokenKey = TokenUtils.buildTokenKey(token);
        if(StringUtils.isEmpty(tokenKey)){
            return false;
        }
        String state = redis.hget(tokenKey,TokenConstant.KEY_TOKEN_TYPE);
        return !(StringUtils.isEmpty(state));
    }

    @Override
    public Long refresh(String token){
        Long er = redis.expire(TokenUtils.buildTokenKey(token),LoginToken);
        if(er != null){
            return er.longValue();
        }
        return -2L;
    }

    @Override
    public boolean loginOut(String token,String accountIp,String accountNo){
        //清空用户用户token
        String accountNoStr = TokenUtils.buildAccountKey(accountIp+"-"+accountNo);
        /*String tokenStr = TokenUtils.buildTokenKey(token);
        String tokenOld = redis.get(accountNoStr);
        if(tokenOld != null){
        }*/
        redis.del(accountNoStr);
        // 删除账号id与token的关系
        redis.del( TokenUtils.buildTokenKey(token));
        return true;
    }

    @Override
    public String getAccount(String token){
        if(StringUtils.isEmpty(token)){
            return null;
        }
        return redis.hget(token, TokenConstant.KEY_TOKEN_ACCOUNT);
    }

}
