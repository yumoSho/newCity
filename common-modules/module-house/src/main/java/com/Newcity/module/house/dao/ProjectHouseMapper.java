package com.Newcity.module.house.dao;

import com.Newcity.module.house.entity.ProjectHouseEntity;

public interface ProjectHouseMapper {
    int deleteByPrimaryKey(String houseId);

    int insert(ProjectHouseEntity record);

    int insertSelective(ProjectHouseEntity record);

    ProjectHouseEntity selectByPrimaryKey(String houseId);

    int updateByPrimaryKeySelective(ProjectHouseEntity record);

    int updateByPrimaryKey(ProjectHouseEntity record);
}