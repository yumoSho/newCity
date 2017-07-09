<%@ page contentType="text/html; charset=gb2312" language="java" errorPage="" %>
<%@ page import="com.yun.portal.UserSession"%> 
<% 
/**
 * Upload pic api 
 * meta 4.1	
 * @author chenhui
 * @date 2010-05-01 
 * @local 西安
 * 带user 图片列表支持
 *
 */	
 
	String path = request.getContextPath(); 
	String local = request.getContextPath()+"/common/up";

	String name = request.getParameter("name");		//父页面：input name
	String view = request.getParameter("view");		//父页面：显示图片ID
	
	

%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base target="_self" />
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title> Upload pic api - 4.1     </title>
<script language="javascript" src="${jquery}"></script>
<script language="javascript">
function login(){
	var uid = $("input[name='uid']").val();
	var pwd = $("input[name='pwd']").val();
	var uid = $.trim(uid);
 	if(uid==""||pwd==""){
		alert("用户名和密码不能为空！");
	} 
}
</script>
<style type="text/css">
body{font-size:12px; margin:0; padding:0; width:100%; height:100%; color:#666666;overflow-x:hidden;overflow-y:hidden; } 
.table{}
.input{ width:80%; height:18px; line-height:18px;}
.button{ width:60px; height:24px; background-color:#48555B; border:1px; color:#FFFFFF}
</style> 
</head>
<body> 
<form action="${path}/login.x" method="post" >
<input type="hidden" name="login_page" value="common/up/img.jsp?name=${param.name}&view=${param.view}" /> 
<table class="table" cellpadding="3" style="width:100%; height:100%" height="100%">
	<tr>
		<td colspan="2" align="left" >登录${sitename} | 账户注册</td>
	</tr> 
	<tr>
		<td colspan="2" 
			style="color:#474A56; font-weight:bold; font-size:12px; line-height:20px; height:20px; " align="left">&nbsp;&nbsp;
			尊敬的用户您好，您还没有登录</td> 
	</tr>
	<tr>
		<td align="right" width="80">用户名：</td>
		<td align="left"><input type="text" name="uid" value=""    class="input"></td>
	</tr>
	<tr>
		<td align="right" width="80">密码：</td>
		<td align="left"><input type="password" name="pwd" value="" class="input"></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td align="left"><input type="submit" value="登录" class="button"  > <a 
			href="#" style="color:#004E00">忘记密码 ？</a></td>
	</tr>
	<tr>
		<td colspan="2" height="40">
			<p style="width:99%;border:1px;border-color:#C9C9C9;border-style:dotted;background-color:#F7F7F7; line-height:18px; " align="left">
				     &nbsp;&nbsp;* 登录后可进行积分、金币等信息的积累
				<br/>&nbsp;&nbsp;* 登录后可以快捷发表评论
				<br/>&nbsp;&nbsp;* ${sitename}会员:<span style="color:#009900">普通会员 、 商家会员 、 联盟会员</span>
</p>
		</td> 
	</tr> 
</table> 
</form> 
</body>
</html>