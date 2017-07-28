package com.Newcity.controller;

import com.Newcity.base.controller.BaseController;
import com.Newcity.lib.utils.StrUtil;
import com.Newcity.libs.filter.utils.TokenUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/7/25.
 */
@Controller
@RequestMapping("/index")
public class IndexController extends BaseController{

    @RequestMapping("/initialize")
    public String index(ModelMap map,HttpServletRequest request, HttpServletResponse response){
        String token = TokenUtils.getValueInRequest(request,"token");
        if(StrUtil.isNull(token)){
            map.put("code","0");
            map.put("errorMessage","登陆异常请跳转到！登陆页面<br>");
            return "error.jsp";
        }
        return "index.jsp";
    }

}
