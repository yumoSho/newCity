package com.Newcity.module.house.dao;

import com.Newcity.module.house.entity.HouseModule;

public interface HouseModuleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HouseModule record);

    int insertSelective(HouseModule record);

    HouseModule selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HouseModule record);

    int updateByPrimaryKey(HouseModule record);
}