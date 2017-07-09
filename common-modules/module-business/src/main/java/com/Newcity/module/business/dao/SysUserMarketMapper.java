package com.Newcity.module.business.dao;

import com.Newcity.libs.sql.ISqlDao;
import com.Newcity.module.business.entity.SysUserMarket;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class SysUserMarketMapper {

    private final String NAMESPACE = "com.Newcity.module.business.dao.SysUserMarketMapper.";

    @Resource
    private ISqlDao sqlDao;


    public int insert(SysUserMarket record){
        return 1;
    }

    public int insertSelective(SysUserMarket record){
        return 1;
    }

    public SysUserMarket selectByPrimaryKey(String id){
        return null;
    }

    public int updateByPrimaryKeySelective(SysUserMarket record){
        return 1;
    }

    public int updateByPrimaryKey(SysUserMarket record){
        return 1;
    }

    public SysUserMarket queryInfoByLogin(SysUserMarket sysUserMarket){
        return sqlDao.get(NAMESPACE+"queryInfoByLogin",sysUserMarket);
    }
}