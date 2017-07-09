package com.Newcity.module.business.dao;

import com.Newcity.module.business.entity.SysUserFinance;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserFinanceMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysUserFinance record);

    int insertSelective(SysUserFinance record);

    SysUserFinance selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysUserFinance record);

    int updateByPrimaryKey(SysUserFinance record);
}