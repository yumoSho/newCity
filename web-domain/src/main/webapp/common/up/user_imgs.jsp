<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<%@ include file="/common/inc/common.jsp"  %> 
<% /*
	* img api 4.0 （frame）
	* 用户照片 
	*/
	boolean userLogin = UserSession.isLogin(request); 
	if(userLogin==false)return;
	String uid = UserSession.getUID(request); 
 
%>
<html>
<head>
	<title>用户照片 </title>
<style type="text/css">
<%
	String css = "";
	//css = "scrollbar-face-color: #FDDEE8; scrollbar-shadow-color: #FDDEE8; scrollbar-highlight-color: #ffffff; scrollbar-3dlight-color: #FDCFDD; scrollbar-darkshadow-color: #FDCFDD; scrollbar-track-color: FFF6F9; scrollbar-arrow-color: #FEA6C0";
	//css = " scrollbar-face-color: #9EBFE8; scrollbar-shadow-color: #FFFFFF; scrollbar-highlight-color: #FFFFFF; scrollbar-3dlight-color: #9EBFE8; scrollbar-darkshadow-color: #9EBFE8; scrollbar-track-color: #FFFFFF; scrollbar-arrow-color: #FFFFFF";
	css = "scrollbar-face-color: #DBEBFE; scrollbar-shadow-color: #B8D6FA; scrollbar-highlight-color: #FFFFFF; scrollbar-3dlight-color: #DBEBFE; scrollbar-darkshadow-color:#458CE4; scrollbar-track-color: #FFFFFF; scrollbar-arrow-color: #458CE4";
	//css = "scrollbar-face-color: #FFFFFF; scrollbar-shadow-color: #D2E5F4; scrollbar-highlight-color: #D2E5F4; scrollbar-3dlight-color: #FFFFFF; scrollbar-darkshadow-color: #FFFFFF; scrollbar-track-color: #FFFFFF; scrollbar-arrow-color: #D2E5F4";
	//css = "scrollbar-shadow-color: #ffffff; scrollbar-highlight-color: #ffffff; scrollbar-face-color: #d9d9d9; scrollbar-3dlight-color: #d9d9d9; scrollbar-darkshadow-color: #d9d9d9; scrollbar-track-color: #ffffff; scrollbar-arrow-color: #ffffff";
	
%>
body{ <%=css%> } 
</style>
</head>
<body bgcolor="#ffffff">
<script language="javascript">
function onMover(obj){
	obj.style.cssText="border:0 solid #0B2565";
	obj.style.filter="alpha(opacity=\'40\')";
	obj.style.cursor="hand";
}

function onMout(obj){
	obj.style.cssText="border:0 solid #0B2565;";
	obj.style.filter = "alpha(opacity=\'100\')";
	obj.style.cursor="hand";
}
function setPhoto(img){
	var id = img.id;
	var src = img.src;
	var url = src+"?photo="+id;
	parent.window.next = 2;  // 设置
	parent.document.getElementById("viewup").src  = url;
	parent.window.returnValue = url;
	parent.viewreturnurl();
}
function getParam(url,name){
	var params = new Array();
	var urls = url.split("?"); 
	var paramArr = urls[1].split("&");
	for(var i=0;i<paramArr.length;i++){
		var temp = paramArr[i].split("=");
		params[temp[0]] = temp[1];    
	}
	var paramValue = params[name];    
	return paramValue;
}
</script>
<UL style="margin:0px;" >
 	<src:file   order="ADD_DATETIME"  uid="<%=uid%>"	> 	
     <li style="float:left;margin-left:5px;margin-right:15px; width:43px; height:80px; font-size:12px; line-height:22px; text-align:center;"><img 
		 width="41" height="41"  style="cursor:hand; border:1 #996699 solid;"
				id="${file.sourceid}"
				src="${path}/${file.url}" 
			 	onmouseover="onMover(this)" 				
				onmouseout="onMout(this)"
				onClick="setPhoto(this)"	
				alt="提示：点击这里" />
	</src:file>
</UL>
</body>
</html>