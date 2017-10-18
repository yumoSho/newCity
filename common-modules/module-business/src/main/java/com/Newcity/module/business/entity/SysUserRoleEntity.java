package com.Newcity.module.business.entity;

import com.Newcity.lib.utils.StrUtil;

import java.util.Date;
import java.util.UUID;

public class SysUserRoleEntity {
    private String roleId;

    private String roleName;

    private String roleType;

    private Date createTime;

    private String operator;

    private Boolean isDelete;

    private Date modificationTime;

    public String getRoleId() {
        if (!StrUtil.isNull(this.roleId)) {
            return this.roleId;
        }
        return this.roleId = UUID.randomUUID().toString().replace("-", "");
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType == null ? null : roleType.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Date getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(Date modificationTime) {
        this.modificationTime = modificationTime;
    }
}