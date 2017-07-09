<%@ page language="java" contentType="text/html; charset=gbk"%>
<% 	
	String path = request.getContextPath();
	String local = request.getContextPath()+"/common/up";
	String fileurl = (String)request.getAttribute("err");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" >
<HTML>
<HEAD>
<title> Error for upload file </title>
<style type="text/css">
.handle{}
.text{color:#330033;}
.bt3 {
	BACKGROUND-IMAGE: url(<%=local%>/img/oao_img_075.gif); 
	WIDTH: 54px; 
	BORDER-TOP-STYLE: none; BORDER-RIGHT-STYLE: none; BORDER-LEFT-STYLE: none; HEIGHT: 22px; 
	BORDER-BOTTOM-STYLE: none
}
.bt4 {
	BACKGROUND-IMAGE: url(<%=local%>/img/oao_img_026.gif); 
	WIDTH: 54px; COLOR: #ffffff; BORDER-TOP-STYLE: none; BORDER-RIGHT-STYLE: none; BORDER-LEFT-STYLE: none; 
	HEIGHT: 22px; BORDER-BOTTOM-STYLE: none
}
</style>
</HEAD>
<body style=" background-color:#D4D0C8; font-size:12px;">
<div id="news_handle" class="handle" >&nbsp;<img 
	src="${path}/common/up/img/err.png" align="absmiddle" />&nbsp;
		Error message :
</div>
<hr >
<div style="background-color:#D4D0C8; font-size:12px; width:100%; padding:5px;" class="root">
	<div id="news" class="text" >
			<OL  style="margin-left:80px;">
				      <%=fileurl%>
			</OL>
			<br/>&nbsp;&nbsp;&nbsp; 	
	</div>
</div>
<div align="center" style="width:98%"> 
		<input type="button" class="bt3" 
		onClick="win_close();" value="¹Ø±Õ"  /> 	
</div>
</body>
</HTML>
<script language="javascript">
function win_close(){
	parent.uploadwin_close();
}
</script>
