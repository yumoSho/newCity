package com.Newcity.libs.filter.filter;

import com.Newcity.libs.dmo.constant.Constant;
import com.Newcity.libs.dmo.constant.ResultCode;
import com.Newcity.libs.dmo.constant.TokenConstant;
import com.Newcity.libs.dmo.vo.JsonResult;
import com.Newcity.libs.filter.WebToken;
import com.Newcity.libs.filter.utils.TokenUtils;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dev on 2017/4/20.
 */
public class WebTokenFiler implements Filter {

    private Logger logger = LoggerFactory.getLogger(WebTokenFiler.class);

    private WebToken webToken;
    // 无token状态下可以访问的uri
    private List<String> allowURI = new ArrayList<String>();
    private FilterConfig filterConfig;

    public void init(FilterConfig config) throws ServletException {
        this.filterConfig = config;
        logger.info("loading WebTokenFilter start!");
//        this.webToken = new WebTokenImpl();
        webToken = WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext()).getBean(WebToken.class);
        //登录请求
        allowURI.add("/login/login");
        //静态页面
        allowURI.add("/css");
        allowURI.add("/js");
        logger.info("loading WebTokenFilter end !");
    }

    public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain) throws ServletException, IOException {
        logger.info("doFilter WebTokenFilter ...");
         HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        if(webToken == null){
            webToken = WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext()).getBean(WebToken.class);
        }

        httpResponse.setHeader("Access-Control-Allow-Origin",httpRequest.getHeader("Origin"));
         httpResponse.setHeader("Access-Control-Allow-Methods","POST,GET,OPTIONS");
         httpResponse.setHeader("Access-Control-Allow-Headers","Content-Type,Set-Cookie,token");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
          httpResponse.setHeader("Access-Control-Max-Age", "60");
        httpResponse.setHeader("Access-Control-Expose-Headers","Content-Type,Set-Cookie,Uf-Session-Id,token");

         if("OPTIONS".equals(httpRequest.getMethod())){
            return;
        }

        JsonResult jsonResult = new JsonResult();
        String token = TokenUtils.getValueInRequest(httpRequest,"token");

        String uri = httpRequest.getRequestURI();
        String projectName = "/"+uri.split("/")[1];

        if(token != null && !"".equals(token.trim())){
            //token是否存在
            if(webToken.isExist(token)){
                logger.info(" token is failure");
                jsonResult.setup(ResultCode.INVALID_TOKEN);
                write(httpResponse,jsonResult);
                return;
            }
            //token 是否登录
            boolean isLogin = false;
            if(webToken.isLogin(token)){
                logger.info(" token is Login success ");
                isLogin = true;
            }
            if(isLogin){
                //更新token时间
                if(webToken.refresh(token) > -1L){
                    chain.doFilter(request,response);
                    return;
                }else{
                    logger.info(" token refresh error   !!!!!!!!!!!!!!!!!");
                    jsonResult.setup(ResultCode.INVALID_TOKEN);
                    write(httpResponse,jsonResult);
                    return;
                }
            }else{
                jsonResult.setup(ResultCode.NEED_LOGIN);
                write(httpResponse,jsonResult);
                return;
            }
        }
        /*else{
            logger.info(" token is null");
            jsonResult.setup(ResultCode.INVALID_TOKEN,"token is null");
            write(response,jsonResult);
            return;
        }*/

        //过滤请求
        for(String url : allowURI){
            if(uri.contains(url)){
                chain.doFilter(request,response);
                return;
            }
        }

        logger.info(" token is null");
        httpResponse.sendRedirect(projectName+"/pro/login/login.jsp");
        jsonResult.setup(ResultCode.INVALID_TOKEN,"token is null");
        write(httpResponse,jsonResult);
        return;
    }

    public void write(ServletResponse response,JsonResult jsonResult) throws IOException{
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSONObject.toJSONString(jsonResult));
        writer.flush();
    }

    public void destroy(){
        this.filterConfig = null;
    }

}
