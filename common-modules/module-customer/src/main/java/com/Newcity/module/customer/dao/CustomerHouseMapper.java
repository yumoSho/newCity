package com.Newcity.module.customer.dao;

import com.Newcity.module.customer.entity.CustomerHouse;

public interface CustomerHouseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CustomerHouse record);

    int insertSelective(CustomerHouse record);

    CustomerHouse selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustomerHouse record);

    int updateByPrimaryKey(CustomerHouse record);
}