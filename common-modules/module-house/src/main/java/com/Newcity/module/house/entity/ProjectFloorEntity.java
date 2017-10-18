package com.Newcity.module.house.entity;

import com.Newcity.lib.utils.StrUtil;

import java.util.Date;
import java.util.UUID;

public class ProjectFloorEntity {
    private String floorId;

    private String emId;

    private String unitNameType;

    private String floorIncrement;

    private String floorName;

    private Byte unitNumber;

    private Integer floorTier;

    private Double floorSpace;

    private Double floorHigit;

    private String spareVarcahr1;

    private String spareVarcahr3;

    private String spareVarcahr2;

    private Date createTime;

    private String operator;

    private Boolean isDelete;

    private Date modificationTime;

    public String getFloorId() {
        if (!StrUtil.isNull(this.floorId)){
            return this.floorId;
        }
        return this.floorId = UUID.randomUUID().toString().replace("-", "");
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId == null ? null : floorId.trim();
    }

    public String getEmId() {
        return emId;
    }

    public void setEmId(String emId) {
        this.emId = emId == null ? null : emId.trim();
    }

    public String getUnitNameType() {
        return unitNameType;
    }

    public void setUnitNameType(String unitNameType) {
        this.unitNameType = unitNameType == null ? null : unitNameType.trim();
    }

    public String getFloorIncrement() {
        return floorIncrement;
    }

    public void setFloorIncrement(String floorIncrement) {
        this.floorIncrement = floorIncrement == null ? null : floorIncrement.trim();
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName == null ? null : floorName.trim();
    }

    public Byte getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(Byte unitNumber) {
        this.unitNumber = unitNumber;
    }

    public Integer getFloorTier() {
        return floorTier;
    }

    public void setFloorTier(Integer floorTier) {
        this.floorTier = floorTier;
    }

    public Double getFloorSpace() {
        return floorSpace;
    }

    public void setFloorSpace(Double floorSpace) {
        this.floorSpace = floorSpace;
    }

    public Double getFloorHigit() {
        return floorHigit;
    }

    public void setFloorHigit(Double floorHigit) {
        this.floorHigit = floorHigit;
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