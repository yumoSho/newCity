package com.Newcity.controller;

import com.Newcity.base.controller.BaseController;
import com.Newcity.lib.utils.IpAddressUtils;
import com.Newcity.lib.utils.MD5Utils;
import com.Newcity.lib.utils.StrUtil;
import com.Newcity.libs.dmo.constant.Constant;
import com.Newcity.libs.dmo.constant.ResultCode;
import com.Newcity.libs.dmo.constant.TokenConstant;
import com.Newcity.libs.dmo.vo.JsonResult;
import com.Newcity.libs.filter.impl.WebTokenImpl;
import com.Newcity.module.business.entity.SysUserMarket;
import com.Newcity.module.business.service.SysUserService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/14.
 */

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private WebTokenImpl webToken;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult login(@RequestBody JSONObject object, HttpServletRequest request,HttpServletResponse response) {
        JsonResult jsonResult = new JsonResult();
        try {
            String account = object.getString("loginName");
            String password = object.getString("password");
            if (StrUtil.isNull(account) || StrUtil.isNull(password)) {
                return jsonResult.setup(ResultCode.INVALID_PARAM, "用户名密码不能为空");
            }
            password = MD5Utils.string2MD5(password);
            Map<String, Object> map = new HashMap<String, Object>();
            String[] role = account.split("@");
            if (role.length < 2 || StrUtil.isNull(role[0]) || StrUtil.isNull(role[1])) {
                return jsonResult.setup(ResultCode.INVALID_PARAM, "用户名格式有误");
            }

            String type = "";
            //通过类型判断
            if (role[0].equals("finance")) {//财务人员
                type = TokenConstant.TOKEN_ROLETYPE_CWRY;
            } else if (role[0].equals("market")) {//销售人员
                type = TokenConstant.TOKEN_ROLETYPE_YXRY;
            } else if (role[0].equals("boss")) {//管理员
                type = TokenConstant.TOKEN_ROLETYPE_BOSS;
            } else if (role[0].equals("admin")) {//管理员
                type = TokenConstant.TOKEN_ROLETYPE_ADMIN;
            }else{
                return jsonResult.setup(ResultCode.INVALID_PARAM, "用户名格式有误");
            }

            SysUserMarket sysUserMarket = sysUserService.login(account,password,type);
            if(sysUserMarket == null){
                return jsonResult.setup(ResultCode.INVALID_PARAM, "用户名密码错误");
            }else if(sysUserMarket.getState() == Constant.USER_FREEZE){
                return jsonResult.setup(ResultCode.INVALID_PARAM, "该用户已经冻结");
            }else if(sysUserMarket.getState() == Constant.USER_DEPARTURE){
                return jsonResult.setup(ResultCode.INVALID_PARAM, "该用户已经离职");
            }

            //获取Ip
            String ip = IpAddressUtils.getIpAddr(request);
            if (!StrUtil.isNull(ip) && !"unknown".equalsIgnoreCase(ip)) {

            }

            if (sysUserMarket.getState() != null && sysUserMarket.getState() == 2) {
                // 离职
                return jsonResult.setup(ResultCode.INVALID_RESULT, "该用户不存在！");
            } else if (sysUserMarket.getState() != null && sysUserMarket.getState() == 1) {
                // 判断是否冻结
                return jsonResult.setup(ResultCode.INVALID_RESULT, "该用户已冻结！");
            }

            //添加token
            Map<String, String> mapToken = new HashMap<String, String>();
            mapToken.put(TokenConstant.KEY_TOKEN_ROLE,role[0]);
            mapToken.put(TokenConstant.KEY_TOKEN_STATE,sysUserMarket.getState().toString());
            mapToken.put(TokenConstant.KEY_TOKEN_ACCOUNTID,sysUserMarket.getId());
            mapToken.put(TokenConstant.KEY_TOKEN_IP,ip);
            mapToken.put(TokenConstant.KEY_TOKEN_ACCOUNT,sysUserMarket.getUserLoginname());
            mapToken.put(TokenConstant.KEY_TOKEN_TYPE,type);
            webToken.getToken(mapToken,response);
            return jsonResult.setup(ResultCode.SUCCESS,sysUserMarket);
        } catch (Exception e) {
            e.printStackTrace();
            error("  " + e.toString());
            return jsonResult.setup(ResultCode.SERVER_ERROR);
        }

    }
}
