package com.Newcity.controller;

import com.Newcity.base.controller.BaseController;
import com.Newcity.libs.dmo.constant.ResultCode;
import com.Newcity.libs.dmo.vo.JsonResult;
import com.Newcity.libs.dmo.vo.Page;
import com.Newcity.libs.filter.utils.TokenUtils;
import com.Newcity.module.business.dao.SysUserDepartmentMapper;
import com.Newcity.module.business.entity.SysUserDepartmentEntity;
import com.Newcity.module.business.service.SysUserDepartmentService;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/29.
 */
@Controller
@RequestMapping("/department")
public class DepartmentController extends BaseController{

    @Autowired
    private SysUserDepartmentService sysUserDepartmentService;

    /*备注：缺少部门名称和部门代码是否重复的方法*/

    /**
     * 进入列表页面
     * @param modleMap
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(ModelMap modleMap, HttpServletRequest request, HttpServletResponse response){
        try{
            Page page = new Page();
            List<SysUserDepartmentEntity> data = sysUserDepartmentService.queryInfo(page);
            Long count = sysUserDepartmentService.queryCount(page);
            modleMap.put("data",data);
            modleMap.put("count",count);
        }catch(Exception e){
            error(e.toString());
            e.printStackTrace();
        }
        return "department/index";
    }

    /**
     * 查询列表数据
     * @param object
     * @param request
     * @return
     */
    @RequestMapping(value = "/queryList.do",method = RequestMethod.POST)
    @ResponseBody
    public Object queryList(@RequestParam(required = false) Object object,HttpServletRequest request){
        try{
            Map<String,Object> map = Maps.newHashMap();
            Page<SysUserDepartmentEntity> page = new Page(request);
            List<SysUserDepartmentEntity> data = sysUserDepartmentService.queryInfo(page);
            Long count = sysUserDepartmentService.queryCount(page);
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

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(HttpServletRequest request,ModelMap modelMap){
        try{
            return "department/add";
        }catch(Exception e){
            error(e.toString());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 添加信息
     * @param object
     * @return
     */
    @RequestMapping(value = "addInfo",method = RequestMethod.POST)
    @ResponseBody
    public Object addinfo(@RequestBody JSONObject object,HttpServletRequest request){
        JsonResult jsonResult = new JsonResult();
        try{
            object.put("operator",getAccountNo(getToken(request)));
            jsonResult = sysUserDepartmentService.FieldIsNull(object);
            if(jsonResult.getResult() != 0){
                return jsonResult;
            }
            sysUserDepartmentService.insert((SysUserDepartmentEntity)jsonResult.getDatas());
            jsonResult.setup(ResultCode.SUCCESS);
            return jsonResult;
        }catch(Exception e){
            error(e.toString());
            e.printStackTrace();
            return jsonResult.setup(ResultCode.SERVER_ERROR);
        }
    }


    /**
     * 查询单个信息
     * @param id
     * @param request
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public String getInfo(@PathVariable String id,HttpServletRequest request,ModelMap modelMap) {
        try{
            SysUserDepartmentEntity entity = sysUserDepartmentService.getInfo(id);
            modelMap.put("entity",entity);
            return "/department/edit";
        }catch(Exception e){
            error(e.toString());
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 查询单个信息
     * @param request
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public String getInfo1(@RequestBody JSONObject object,HttpServletRequest request,ModelMap modelMap) {
        try{
            SysUserDepartmentEntity entity = sysUserDepartmentService.getInfo(object.getString("id"));
            modelMap.put("entity",entity);
            return "department/edit";
        }catch(Exception e){
            error(e.toString());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 修改信息
     * @param object
     * @return
     */
    @RequestMapping(value = "update",method = RequestMethod.POST)
    @ResponseBody
    public Object updateInfo(@RequestBody JSONObject object,HttpServletRequest request){
        JsonResult jsonResult = new JsonResult();
        try{
            object.put("operator",getAccountNo(getToken(request)));
            jsonResult = sysUserDepartmentService.FieldIsNull(object);
            if(jsonResult.getResult() != 0){
                return jsonResult;
            }
            sysUserDepartmentService.updateByPrimaryKeySelective((SysUserDepartmentEntity)jsonResult.getDatas());
            jsonResult.setup(ResultCode.SUCCESS);
            return jsonResult;
        }catch(Exception e){
            error(e.toString());
            e.printStackTrace();
            return jsonResult.setup(ResultCode.SERVER_ERROR);
        }
    }

    /**
     * 删除信息
     * @return
     */
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    @ResponseBody
    public Object deleteInfo(@RequestParam(value = "id",required = false) String[] ids,HttpServletRequest request){
        JsonResult jsonResult = new JsonResult();
        try{
            Map<String,Object> map = Maps.newHashMap();
            map.put("ids",ids);
            sysUserDepartmentService.deleteInfo(map);
            jsonResult.setup(ResultCode.SUCCESS);
            return jsonResult;
        }catch(Exception e){
            error(e.toString());
            e.printStackTrace();
            return jsonResult.setup(ResultCode.SERVER_ERROR);
        }
    }





    







}
