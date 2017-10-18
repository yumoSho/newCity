package com.Newcity.controller;

import com.Newcity.base.controller.BaseController;
import com.Newcity.libs.dmo.constant.ResultCode;
import com.Newcity.libs.dmo.vo.JsonResult;
import com.Newcity.libs.dmo.vo.Page;
import com.Newcity.libs.filter.utils.TokenUtils;
import com.Newcity.module.business.entity.SysUserDepartmentEntity;
import com.Newcity.module.business.entity.SysUserEntity;
import com.Newcity.module.business.service.SysUserDepartmentService;
import com.Newcity.module.business.service.SysUserService;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/1.
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController extends BaseController{

    @Autowired
    private SysUserDepartmentService sysUserDepartmentService;
    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(value = "add",method = RequestMethod.GET)
    public String add(ModelMap modelMap){
        List<SysUserDepartmentEntity> entityList = sysUserDepartmentService.getDepartmentName();
        modelMap.put("departmentList",entityList);
        //添加部门下拉列表
        return "/employee/add";
    }

    @RequestMapping(value = "index",method = RequestMethod.GET)
    public String index(){
        return "/employee/index";
    }

    @RequestMapping(value = "edit/{id}",method = RequestMethod.GET)
    public String edit(@PathVariable String id, HttpServletRequest request, ModelMap modelMap){
        try{
            List<SysUserDepartmentEntity> entityList = sysUserDepartmentService.getDepartmentName();
            SysUserEntity entity = sysUserService.getInfo(id);
            modelMap.put("departmentList",entityList);
            modelMap.put("entity",entity);
            return "/employee/edit";
        }catch(Exception e){
            e.printStackTrace();
            return "/employee/index";
        }
    }

    /**
     * 添加信息
     *  模块规则：
     *  1、密码默认6个6
     *  2、用户+@+部门代码 生成用户名
     *  3、用户编号通过部门编号开头，后四位为部门排序
     *  4、用户Id是UUID
     * @param object
     * @return
     */
    @RequestMapping(value = "addInfo",method = RequestMethod.POST)
    @ResponseBody
    public Object addinfo(@RequestBody JSONObject object,HttpServletRequest request){
        JsonResult jsonResult = new JsonResult();
        try{
            object.put("operator",getAccountNo(getToken(request)));
            jsonResult = sysUserService.FieldIsNull(object);
            if(jsonResult.getResult() != 0){
                return jsonResult;
            }
            sysUserService.insert((SysUserEntity)jsonResult.getDatas());
            jsonResult.setup(ResultCode.SUCCESS);
            return jsonResult;
        }catch(Exception e){
            error(e.toString());
            e.printStackTrace();
            return jsonResult.setup(ResultCode.SERVER_ERROR);
        }
    }

    /**
     * 查询人员信息
     * @param request
     * @return
     */
    @RequestMapping(value="queryList.do",method = RequestMethod.POST)
    @ResponseBody
    public Object list(HttpServletRequest request){
        try{
            Map<String,Object> map = Maps.newHashMap();
            Page<SysUserEntity> page = new Page(request);
            List<SysUserEntity> data = sysUserService.queryInfo(page);
            Long count = sysUserService.queryCount(page);
            page.setData(data);
            page.setRecords(count.intValue());
            System.out.print(page);
            return page;
        }catch(Exception e){
            error(e.toString());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 人员信息修改
     * @return
     */
    @RequestMapping(value="employee.update",method=RequestMethod.POST)
    @ResponseBody
    public Object update(@RequestBody JSONObject object,HttpServletRequest request){
        JsonResult jsonResult = new JsonResult();
        try{
            object.put("operator",getAccountNo(getToken(request)));
            jsonResult = sysUserService.FieldIsNull(object);
            if(jsonResult.getResult() != 0){
                return jsonResult;
            }
            sysUserService.updateByPrimaryKeySelective((SysUserEntity)jsonResult.getDatas());
            jsonResult.setup(ResultCode.SUCCESS);
            return jsonResult;
        }catch(Exception e){
            e.printStackTrace();
            return jsonResult.setup(ResultCode.SERVER_ERROR);
        }
    }






}
