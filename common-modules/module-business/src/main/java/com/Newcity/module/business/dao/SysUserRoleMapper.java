package com.Newcity.module.business.dao;

import com.Newcity.module.business.entity.SysUserRoleEntity;

public interface SysUserRoleMapper {
    int deleteByPrimaryKey(String roleId);

    int insert(SysUserRoleEntity record);

    int insertSelective(SysUserRoleEntity record);

    SysUserRoleEntity selectByPrimaryKey(String roleId);

    int updateByPrimaryKeySelective(SysUserRoleEntity record);

    int updateByPrimaryKey(SysUserRoleEntity record);
}