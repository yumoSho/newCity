package com.Newcity.module.house.entity;

import com.Newcity.lib.utils.StrUtil;

import java.util.Date;
import java.util.UUID;

public class ProjectEstateEntity {
    private String emId;

    private String emName;

    private String emIntro;

    private String emAddress;

    private String contact;

    private Date createTime;

    private String operator;

    private Boolean isDelete;

    private Date modificationTime;

    public String getEmId() {
        if (!StrUtil.isNull(this.emId)) {
            return this.emId;
        }
        return this.emId = UUID.randomUUID().toString().replace("-", "");
    }

    public void setEmId(String emId) {
        this.emId = emId == null ? null : emId.trim();
    }

    public String getEmName() {
        return emName;
    }

    public void setEmName(String emName) {
        this.emName = emName == null ? null : emName.trim();
    }

    public String getEmIntro() {
        return emIntro;
    }

    public void setEmIntro(String emIntro) {
        this.emIntro = emIntro == null ? null : emIntro.trim();
    }

    public String getEmAddress() {
        return emAddress;
    }

    public void setEmAddress(String emAddress) {
        this.emAddress = emAddress == null ? null : emAddress.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
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