package com.Newcity.module.business.service;

import com.Newcity.base.service.BaseService;
import com.Newcity.lib.utils.StrUtil;
import com.Newcity.libs.dmo.constant.ResultCode;
import com.Newcity.libs.dmo.vo.JsonResult;
import com.Newcity.libs.dmo.vo.Page;
import com.Newcity.module.business.dao.SysUserDepartmentMapper;
import com.Newcity.module.business.entity.SysUserDepartmentEntity;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/29.
 */
@Service
public class SysUserDepartmentService extends BaseService {

    @Autowired
    private SysUserDepartmentMapper sysUserDepartmentMapper;

    public JsonResult FieldIsNull(JSONObject object){
        JsonResult jsonResult = new JsonResult();
        SysUserDepartmentEntity sysAdminEntity = new SysUserDepartmentEntity();
        String departmentName = object.getString("departmentName");
        if(StrUtil.isNull(departmentName)){
            return jsonResult.setup(ResultCode.INVALID_PARAM,"部门名称不能为空");
        }else{
            sysAdminEntity.setDepartmentName(departmentName);
        }
        String departmentKey = object.getString("departmentKey");
        if(StrUtil.isNull(departmentKey)){
            return jsonResult.setup(ResultCode.INVALID_PARAM,"部门编号不能为空");
        }else{
            sysAdminEntity.setDepartmentKey(departmentKey);
        }
        if(object.getString("operator") != null){
            sysAdminEntity.setOperator(object.getString("operator"));
        }
        if(object.getString("departmentInfo") != null){
            sysAdminEntity.setDepartmentInfo(object.getString("departmentInfo"));
        }
        if(object.getInteger("departmentId") != null){
            sysAdminEntity.setDepartmentId(object.getInteger("departmentId"));
        }
        return jsonResult.setup(ResultCode.SUCCESS,sysAdminEntity);
    }


    /**
     * 分页方法
     * @param page
     * @return
     */
    public List<SysUserDepartmentEntity> queryInfo(Page page){
        return sysUserDepartmentMapper.queryInfo(page);
    }

    public Long queryCount(Page page){
        return sysUserDepartmentMapper.queryCount(page);
    }

    public SysUserDepartmentEntity getInfo(String id){
        return sysUserDepartmentMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(SysUserDepartmentEntity record){
        return sysUserDepartmentMapper.updateByPrimaryKeySelective(record);
    }

    public int insert(SysUserDepartmentEntity record){
        return sysUserDepartmentMapper.insert(record);
    }

    public int deleteInfo(Map<String,Object> map){
        return sysUserDepartmentMapper.deleteInfo(map);
    }

    public List<SysUserDepartmentEntity> getDepartmentName(){
        return sysUserDepartmentMapper.getDepartmentName();
    }
}
