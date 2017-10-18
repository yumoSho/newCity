package com.Newcity.module.house.entity;

import com.Newcity.lib.utils.StrUtil;

import java.util.Date;
import java.util.UUID;

public class ProjectUnitEntity {
    private String unitId;

    private String floorId;

    private String unitType;

    private String unitName;

    private String spareVarcahr1;

    private String spareVarcahr3;

    private String spareVarcahr2;

    private Date createTime;

    private String operator;

    private Boolean isDelete;

    private Date modificationTime;

    public String getUnitId() {
        if (!StrUtil.isNull(this.unitId)){
            return this.unitId;
        }
        return this.unitId = UUID.randomUUID().toString().replace("-", "");
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId == null ? null : unitId.trim();
    }

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId == null ? null : floorId.trim();
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType == null ? null : unitType.trim();
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName == null ? null : unitName.trim();
    }

    public String getSpareVarcahr1() {
        return spareVarcahr1;
    }

    public void setSpareVarcahr1(String spareVarcahr1) {
        this.spareVarcahr1 = spareVarcahr1 == null ? null : spareVarcahr1.trim();
    }

    public String getSpareVarcahr3() {
        return spareVarcahr3;
    }

    public void setSpareVarcahr3(String spareVarcahr3) {
        this.spareVarcahr3 = spareVarcahr3 == null ? null : spareVarcahr3.trim();
    }

    public String getSpareVarcahr2() {
        return spareVarcahr2;
    }

    public void setSpareVarcahr2(String spareVarcahr2) {
        this.spareVarcahr2 = spareVarcahr2 == null ? null : spareVarcahr2.trim();
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