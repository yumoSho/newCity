package com.Newcity.module.customer.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CustomerVip {
    private Long id;

    private BigDecimal codeMoney;

    private Long customerId;

    private Date createTime;

    private String operator;

    private Boolean isDelete;

    private Date modificationTime;

    private Boolean auditState;

    private String rejectCause;

    private Integer paymentState;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getCodeMoney() {
        return codeMoney;
    }

    public void setCodeMoney(BigDecimal codeMoney) {
        this.codeMoney = codeMoney;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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

    public Boolean getAuditState() {
        return auditState;
    }

    public void setAuditState(Boolean auditState) {
        this.auditState = auditState;
    }

    public String getRejectCause() {
        return rejectCause;
    }

    public void setRejectCause(String rejectCause) {
        this.rejectCause = rejectCause == null ? null : rejectCause.trim();
    }

    public Integer getPaymentState() {
        return paymentState;
    }

    public void setPaymentState(Integer paymentState) {
        this.paymentState = paymentState;
    }
}