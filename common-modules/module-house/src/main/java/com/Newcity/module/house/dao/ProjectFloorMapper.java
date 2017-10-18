package com.Newcity.module.house.dao;

import com.Newcity.module.house.entity.ProjectFloorEntity;

public interface ProjectFloorMapper {
    int deleteByPrimaryKey(String floorId);

    int insert(ProjectFloorEntity record);

    int insertSelective(ProjectFloorEntity record);

    ProjectFloorEntity selectByPrimaryKey(String floorId);

    int updateByPrimaryKeySelective(ProjectFloorEntity record);

    int updateByPrimaryKey(ProjectFloorEntity record);
}