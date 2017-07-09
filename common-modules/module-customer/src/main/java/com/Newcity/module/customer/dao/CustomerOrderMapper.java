package com.Newcity.module.customer.dao;

import com.Newcity.module.customer.entity.CustomerOrder;

public interface CustomerOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CustomerOrder record);

    int insertSelective(CustomerOrder record);

    CustomerOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustomerOrder record);

    int updateByPrimaryKey(CustomerOrder record);
}