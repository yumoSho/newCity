package com.Newcity.module.house.dao;

import com.Newcity.module.house.entity.ProjectUnitEntity;

public interface ProjectUnitMapper {
    int deleteByPrimaryKey(String unitId);

    int insert(ProjectUnitEntity record);

    int insertSelective(ProjectUnitEntity record);

    ProjectUnitEntity selectByPrimaryKey(String unitId);

    int updateByPrimaryKeySelective(ProjectUnitEntity record);

    int updateByPrimaryKey(ProjectUnitEntity record);
}