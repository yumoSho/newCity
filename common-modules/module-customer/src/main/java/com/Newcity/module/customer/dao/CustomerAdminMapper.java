package com.Newcity.module.customer.dao;

import com.Newcity.module.customer.entity.CustomerAdmin;

public interface CustomerAdminMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CustomerAdmin record);

    int insertSelective(CustomerAdmin record);

    CustomerAdmin selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustomerAdmin record);

    int updateByPrimaryKey(CustomerAdmin record);
}