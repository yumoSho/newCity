<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ include file="/common/taglibs.jspf" %>
<%@ page import="com.Newcity.libs.filter.constants.SessionKey" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <%@include file="/common/elibs.jspf" %>
    
</head>
<body>

<%@ include file="/common/head.jspf"%>
<%@ include file="/common/sidebar.jspf"%>
<div id="content">
    <div id="content-header">
        <div id="breadcrumb"> <a href="index.html" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> 首页</a></div>
    </div>
    <div position="center" id="framecenter">
        <div tabid="home" title="我的主页" style="height:300px" >
            <iframe frameborder="0" name="home" id="home" src="${path}/pro/welcome/welcome.jsp"></iframe>
        </div>
    </div>
</div>


</body>
</html>