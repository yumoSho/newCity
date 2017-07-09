package com.Newcity.module.business.service;

import com.Newcity.base.service.BaseService;
import com.Newcity.module.business.dao.SysUserMarketMapper;
import com.Newcity.module.business.entity.SysUserMarket;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/5/14.
 */
@Service
public class SysUserService extends BaseService {

    @Autowired
    private SysUserMarketMapper sysUserMarketMapper;


    public SysUserMarket login(String loginName,String password,String type){
        try{
            info("login begin...");
            SysUserMarket sysUserMarket = new SysUserMarket();
            sysUserMarket.setUserLoginname(loginName);
            sysUserMarket.setPassword(password);
            sysUserMarket.setUserType(type);
            return sysUserMarketMapper.queryInfoByLogin(sysUserMarket);
        }catch(Exception e){
            e.printStackTrace();
            error("login "+e.toString());
            return null;
        }
    }
}
