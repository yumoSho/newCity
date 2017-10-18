package com.Newcity.module.house.entity;

import com.Newcity.lib.utils.StrUtil;

import java.util.Date;
import java.util.UUID;

public class ProjectHouseEntity {
    private String houseId;

    private String unitId;

    private Double houseArea;

    private Integer houseNo;

    private Integer floorHigh;

    private String houseType;

    private String fitmentType;

    private String orientation;

    private String spareVarcahr1;

    private String spareVarcahr3;

    private String spareVarcahr2;

    private Date createTime;

    private String operator;

    private Boolean isDelete;

    private Date modificationTime;

    public String getHouseId() {
        if (!StrUtil.isNull(this.houseId)){
            return this.houseId;
        }
        return this.houseId = UUID.randomUUID().toString().replace("-", "");
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId == null ? null : houseId.trim();
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId == null ? null : unitId.trim();
    }

    public Double getHouseArea() {
        return houseArea;
    }

    public void setHouseArea(Double houseArea) {
        this.houseArea = houseArea;
    }

    public Integer getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(Integer houseNo) {
        this.houseNo = houseNo;
    }

    public Integer getFloorHigh() {
        return floorHigh;
    }

    public void setFloorHigh(Integer floorHigh) {
        this.floorHigh = floorHigh;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType == null ? null : houseType.trim();
    }

    public String getFitmentType() {
        return fitmentType;
    }

    public void setFitmentType(String fitmentType) {
        this.fitmentType = fitmentType == null ? null : fitmentType.trim();
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation == null ? null : orientation.trim();
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