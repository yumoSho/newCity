package com.Newcity.base.controller;

import com.Newcity.base.entity.BaseEntity;
import com.Newcity.base.service.BaseService;
import com.Newcity.libs.dmo.vo.Page;
import com.Newcity.libs.filter.impl.WebTokenImpl;
import com.Newcity.libs.logger.ILogger;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Map;

/**
 * Created by dev on 2017/5/3.
 */
public class BaseController<E> implements ILogger {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WebTokenImpl webToken;
    /*private String requestName;
    private BaseService servicwe;
    private BaseEntity entity;

    protected void setEntity(BaseEntity entity) {
        this.entity = entity;
    }

    protected void setServicwe(BaseService servicwe) {
        this.servicwe = servicwe;
    }

    protected void setRequestName(String requestName) {
        this.requestName = requestName;
    }*/

    public void info(String message){
        logger.info(" Controller  info  "+message);
    }
    public void debug(String message){
        logger.debug(" Controller debug "+message);
    }
    public void error(String message){
        logger.error(" Controller error "+message);
    }
    public void warn(String message){
        logger.warn(" Controller warn "+message);
    }

    /**
     * 进入列表页面
     * @param modleMap
     * @param request
     * @param response
     * @return
     */
   /* @RequestMapping(value = "/index")
    public String index(ModelMap modleMap, HttpServletRequest request, HttpServletResponse response){
        try{
            Page page = new Page();
            List<E> data = servicwe.queryInfo(page);
            Long count = servicwe.queryCount(page);
            modleMap.put("data",data);
            modleMap.put("count",count);
        }catch(Exception e){
            error(e.toString());
            e.printStackTrace();
        }
        return "department/index";
    }*/

    /**
     * 查询列表数据
     * @param object
     * @param request
     * @return
     */
    /*@RequestMapping(value = "/queryList.do",method = RequestMethod.POST)
    @ResponseBody
    public Object queryList(@RequestParam(required = false) Object object, HttpServletRequest request){
        try{
            Map<String,Object> map = Maps.newHashMap();
            Page<E> page = new Page(request);
            List<E> data = servicwe.queryInfo(page);
            Long count = servicwe.queryCount(page);
            page.setData(data);
            page.setRecords(count.intValue());
            System.out.print(page);
            return page;
        }catch(Exception e){
            error(e.toString());
            e.printStackTrace();
            return null;
        }
    }*/

    /**
     * 查询单个信息
     * @param id
     * @param request
     * @param modelMap
     * @return
     */
    /*@RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public String getInfo(@PathVariable String id, HttpServletRequest request, ModelMap modelMap) {
        try{
            Object entity = servicwe.getInfo(id);
            modelMap.put("entity",entity);
            return "department/edit";
        }catch(Exception e){
            error(e.toString());
            e.printStackTrace();
            return null;
        }
    }*/


    /**
     * 获取用户Id
     * @param token
     * @return
     */
    public String getAccountId(String token){
        return webToken.getAccountId(token);
    }

    /**
     * 获取用户Ip
     * @param token
     * @return
     */
    public String isAccountIp(String token){
        return webToken.isAccountIp(token);
    }
}
