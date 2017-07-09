<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.Newcity.libs.filter.constants.SessionKey" %>
<%
	String role = (String)session.getAttribute( SessionKey.ACCOUNT_ROLE );
	boolean isAdmin = false;
	if( role == null  ){
		return;
	}
	if( "admin".equals(role)){
		isAdmin = true;
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>${app.name}</title>
	<!--[if lt IE 10]>
	<script>
		alert("您的IE浏览器版本过低，请升级到IE10及以上版本")
		window.location.href = "../../oldIe.html";
	</script>
	<![endif]-->
	<link href="${ligerUI}/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
	<link href="${path}/js/ligerUI/skins_/base.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${path}/js/jquery/jquery-1.9.0.min.js" ></script>
	<script type="text/javascript" src="${path}/js/common/local.js"></script>
	<script type="text/javascript" src="${path}/js/jquery/jquery.cookie.js"></script>
	<script type="text/javascript" src="${ligerUI}/js/ligerui.all.js" ></script>
	<script type="text/javascript" src="${ligerUI}/js/plugins/ligerTab.js"></script>
	<script type="text/javascript" src="${path}/js/json/json2.js"></script>
	<script type="text/javascript">
        var ligerUI_path = "${ligerUI}";
	</script>
	<script type="text/javascript" src="${path}/js/ligerUI/js_/index.ext.js"></script>
</head>
<body style="padding:0px;background:#EAEEF5;">
<div id="pageloading"></div>
<div id="topmenu" class="l-topmenu">
	<div class="l-topmenu-logo">${app.name} </div>
	<div class="l-topmenu-welcome">

		<label> 账户 ${account_id}  登录系统：</label>
		<select id="skinSelect1">
			<option value="aqua" selected="selected">平台管理</option>
			<option value="silvery">CRM客户管理</option>
			<option value="gray">OA办公</option>
			<option value="gray2014" >客服平台</option>
		</select>
		<span class="space">|</span>
		<a href="${path}/logout" class="l-link2" target="_top">安全退出</a>
	</div>
</div>
<div id="layout1" style="width:99.2%; margin:0 auto; margin-top:4px;" >

	<div position="left"  title="菜单" id="accordion1">
		<div title="运营中心">
			<ul id="tree3" style="margin-top:3px;"></ul>
		</div>
		<% if(isAdmin){ %>
		<div title="系统管理"  >
			<ul id="tree1" style="margin-top:3px;"></ul>
		</div>

		<div title="账户管理"  >
			<ul id="tree2" style="margin-top:3px;"></ul>
		</div>
		<% } %>
	</div>

	<div position="center" id="framecenter">
		<div tabid="home" title="我的主页" style="height:300px" >
			<iframe frameborder="0" name="home" id="home" src="welcome/welcome.jsp"></iframe>
		</div>
	</div>

</div>
<div  style="height:32px; line-height:32px; text-align:center;">
	Copyright © 2011-2014 www.igrowface.com
</div>
<div style="display:none"></div>
</body>
</html>
<script type="text/javascript">
    <%@ include file="index.tree.jsp" %>
</script>