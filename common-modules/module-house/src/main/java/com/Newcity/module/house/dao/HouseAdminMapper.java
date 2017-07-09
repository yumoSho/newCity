package com.Newcity.module.house.dao;

import com.Newcity.module.house.entity.HouseAdmin;

public interface HouseAdminMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HouseAdmin record);

    int insertSelective(HouseAdmin record);

    HouseAdmin selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HouseAdmin record);

    int updateByPrimaryKey(HouseAdmin record);
}