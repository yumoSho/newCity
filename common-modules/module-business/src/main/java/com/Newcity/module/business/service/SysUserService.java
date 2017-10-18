package com.Newcity.module.business.service;

import com.Newcity.base.service.BaseService;
import com.Newcity.lib.utils.MD5Utils;
import com.Newcity.lib.utils.StrUtil;
import com.Newcity.libs.dmo.constant.ResultCode;
import com.Newcity.libs.dmo.vo.JsonResult;
import com.Newcity.libs.dmo.vo.Page;
import com.Newcity.module.business.dao.SysUserMapper;
import com.Newcity.module.business.entity.SysUserEntity;
import com.Newcity.module.business.util.NumberUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/5/14.
 */
@Service
public class SysUserService extends BaseService {

    private final Integer LENGTH = 5;//生成Id的个数

    @Autowired
    private SysUserMapper sysUserMapper;


    public SysUserEntity login(String loginName, String password, String type){
        try{
            info("login begin...");
            SysUserEntity sysUserMarketEntity = new SysUserEntity();
            sysUserMarketEntity.setUserLoginname(loginName);
            sysUserMarketEntity.setPassword(password);
            sysUserMarketEntity.setUserType(type);
            return sysUserMapper.queryInfoByLogin(sysUserMarketEntity);
        }catch(Exception e){
            e.printStackTrace();
            error("login "+e.toString());
            return null;
        }
    }

    public JsonResult FieldIsNull(JSONObject object){
        JsonResult jsonResult = new JsonResult();
        try{
            SysUserEntity entity = new SysUserEntity();
            String userLoginName = object.getString("userLoginName");
            if(StrUtil.isNull(userLoginName)){
                return jsonResult.setup(ResultCode.INVALID_PARAM,"用户名不能为空");
            }
            String userName = object.getString("userName");
            if(StrUtil.isNull(userName)){
                return jsonResult.setup(ResultCode.INVALID_PARAM,"用户名称不能为空");
            }else{
                entity.setUserName(userName);
            }

            String userPhone = object.getString("userPhone");
            if(StrUtil.isNull(userPhone)){
                return jsonResult.setup(ResultCode.INVALID_PARAM,"手机号不能为空");
            }else{
                entity.setUserPhone(userPhone);
            }
            String departmentNumber = object.getString("departmentNumber");
            if(StrUtil.isNull(departmentNumber)){
                return jsonResult.setup(ResultCode.INVALID_PARAM,"部门编号不能为空");
            }else{
                entity.setDepartmentNumber(departmentNumber);
            }
            String userType = object.getString("userType");
            if(StrUtil.isNull(userType)){//用户名为 用户+@+部门代码
                return jsonResult.setup(ResultCode.INVALID_PARAM,"部门类型不能为空");
            }else{
                entity.setUserType(userType);
                entity.setUserLoginname(userLoginName+"@" +
                        ""+userType);
            }
            String state = object.getString("state");
            if(StrUtil.isNull(state)){
                entity.setState(0);
            }else{
                entity.setState(object.getInteger("state"));
            }
            if(object.getString("userEmail") != null){
                entity.setUserEmail(object.getString("userEmail"));
            }
            if(object.getString("userAddress") != null){
                entity.setUserAddress(object.getString("userAddress"));
            }
            if(object.getString("operator") != null){
                entity.setOperator(object.getString("operator"));
            }
            if(object.getString("id") != null){
                entity.setId(object.getString("id"));
            }
            if(object.getString("password") == null){
                entity.setPassword(MD5Utils.string2MD5("666666"));//默认密码
            }else{
                entity.setPassword(object.getString("password"));
            }
            if(object.getString("userJobAge") != null){
                entity.setUserJobAge(object.getInteger("userJobAge"));
            }
            return jsonResult.setup(ResultCode.SUCCESS,entity);
        }catch(Exception e){
            e.printStackTrace();
            return jsonResult.setup(ResultCode.SERVER_ERROR,"参数出现问题");
        }
    }

    public SysUserEntity getAccountById(String id){
        return sysUserMapper.selectByPrimaryKey(id);
    }

    public int insert(SysUserEntity entity){
        if(entity == null){
            return -1;
        }
        Long number = sysUserMapper.getDepartmentByCount(entity.getDepartmentNumber());
        number += 1;
        entity.setUserNumber(entity.getDepartmentNumber() + NumberUtil.ById(LENGTH,number.toString()));
        return sysUserMapper.insert(entity);
    }

    public List<SysUserEntity> queryInfo(Page page){
        return sysUserMapper.queryInfo(page);
    }

    public Long queryCount(Page page){
        return sysUserMapper.queryCount(page);
    }

    public boolean isExistLoginName(String loginName){
        return sysUserMapper.isExistLoginName(loginName) > 0;
    }

    public SysUserEntity getInfo(String id){
        return sysUserMapper.getInfo(id);
    }

    public Integer updateByPrimaryKeySelective(SysUserEntity entity){
        return sysUserMapper.updateByPrimaryKeySelective(entity);
    }





}
