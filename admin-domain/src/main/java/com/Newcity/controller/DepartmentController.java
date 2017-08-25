package com.Newcity.controller;

import com.Newcity.base.controller.BaseController;
import com.Newcity.libs.dmo.constant.ResultCode;
import com.Newcity.libs.dmo.vo.JsonResult;
import com.Newcity.libs.dmo.vo.Page;
import com.Newcity.module.business.entity.SysUserDepartmentEntity;
import com.Newcity.module.business.service.SysUserDepartmentService;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/29.
 */
@Controller
@RequestMapping("/pro/department")
public class DepartmentController extends BaseController{

    @Autowired
    private SysUserDepartmentService sysUserDepartmentService;

    @RequestMapping(value = "/queryInfo", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult queryInfo(@RequestBody JSONObject object ) {
        JsonResult jsonResult = new JsonResult();
        try{
            Page page = new Page(object);
            List<SysUserDepartmentEntity> data = sysUserDepartmentService.queryInfo(page);
            Long count = sysUserDepartmentService.queryCount(page);
            Map<String,Object> map = Maps.newHashMap();
            map.put("data",data);
            map.put("count",count);
            return jsonResult.setup(ResultCode.SUCCESS,map);
        }catch(Exception e){
            error(e.toString());
            e.printStackTrace();
            return jsonResult.setup(ResultCode.SERVER_ERROR);
        }
    }

}
