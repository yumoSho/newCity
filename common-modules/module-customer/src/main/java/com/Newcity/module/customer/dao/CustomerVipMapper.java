package com.Newcity.module.customer.dao;

import com.Newcity.module.customer.entity.CustomerVip;

public interface CustomerVipMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CustomerVip record);

    int insertSelective(CustomerVip record);

    CustomerVip selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustomerVip record);

    int updateByPrimaryKey(CustomerVip record);
}