package com.Newcity.module.business.entity;

import com.alibaba.fastjson.JSONObject;

import java.util.Date;

public class SysUserMarket {
    private String id;

    private String userName;

    private String userPhone;

    private String userEmail;

    private String userLoginname;

    private Integer userJobAge;

    private String userAddress;

    private Integer state;

    private String parentsId;

    private Date createTime;

    private String operator;

    private Boolean isDelete;

    private Date modificationTime;

    private String userType;

    private String password;

    public SysUserMarket() {
    }

    public SysUserMarket(JSONObject object) throws Exception {
        this.password = object.getString("password");
        this.userName = object.getString("userName");
        this.userPhone = object.getString("userPhone");
        this.userEmail = object.getString("userEmail");
        this.userLoginname = object.getString("userLoginname");
        this.userJobAge = object.getInteger("userJobAge");
        this.userAddress = object.getString("userAddress");
        this.state = object.getInteger("userJobAge");
        this.parentsId = object.getString("parentsId");
        this.createTime = object.getDate("cteateTime");
        this.operator = object.getString("operator");
        this.modificationTime = object.getDate("cteateTime");
        this.userType = object.getString("userType");
        this.id = object.getString("id");
    }

    public SysUserMarket(JSONObject object, String type) throws Exception {
        this.password = object.getString("password");
        this.userName = object.getString("userName");
        this.userPhone = object.getString("userPhone");
        this.userEmail = object.getString("userEmail");
        this.userLoginname = object.getString("userLoginname");
        this.userJobAge = object.getInteger("userJobAge");
        this.userAddress = object.getString("userAddress");
        this.state = object.getInteger("userJobAge");
        this.parentsId = object.getString("parentsId");
        this.createTime = object.getDate("cteateTime");
        this.operator = object.getString("operator");
        this.modificationTime = object.getDate("cteateTime");
        this.userType = type;
        this.id = object.getString("id");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public String getUserLoginname() {
        return userLoginname;
    }

    public void setUserLoginname(String userLoginname) {
        this.userLoginname = userLoginname == null ? null : userLoginname.trim();
    }

    public Integer getUserJobAge() {
        return userJobAge;
    }

    public void setUserJobAge(Integer userJobAge) {
        this.userJobAge = userJobAge;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress == null ? null : userAddress.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getParentsId() {
        return parentsId;
    }

    public void setParentsId(String parentsId) {
        this.parentsId = parentsId == null ? null : parentsId.trim();
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}