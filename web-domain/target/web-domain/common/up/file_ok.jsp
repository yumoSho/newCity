<%@ page contentType="text/html; charset=gbk" language="java" %>
<%@ page import="java.util.List" %>  
<%@ page import="com.commons.framework.config.RequestScope" %>
<%@ page import="com.yun.resource.file.bean.ResourceFile" %>
<%
   String path = request.getContextPath();
   String local = request.getContextPath()+"/common/up";
   List files = (List)request.getAttribute(RequestScope.UPLOAD_FILES); 
   if(files==null||files.size()<=0){
   		out.print("上传失败！");
		return;
   }
   int size = files.size();
   int type = 1;
   String pack = "id";
   //type="1"  单文件模式 URL upload/1.jpg
   //type="2"  多文件模式 URL upload/1.jpg|upload/1.jpg|upload/1.jpg
   //pack="ID" 
   //		type=1	|1033?type=1&pack=id
   //       type=2  |1033|1033|1033|1033|1033|1033?type=1&pack=id
   //pack="URL"
   //		type=1	|upload/1.jpg?type=1&pack=url
   //       type=2  |upload/1.jpg|upload/1.jpg|upload/1.jpg?type=1&pack=url
   //pack="IDURL"
   //		type=1	|1033=upload/1.jpg?type=1&pack=idurl
   //       type=2  |1033=upload/1.jpg|1033=upload/1.jpg|1033=upload/1.jpg?type=1&pack=idurl
   String urls = "";
%> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<style type="text/css">
.handle{}
.text{color:#330033;}
.bt3 { float:right; margin-right:80px;} 
</style>
</head>
<body style=" background-color:#D4D0C8; font-size:12px;">
<div id="news_handle" class="handle" >&nbsp; >&nbsp;
		文件地址：${dominurl}/
</div> 
<hr/>
<div style="background-color:#D4D0C8; font-size:12px; width:98%; padding:5px;" class="root">
	<div id="news" class="text" >
			<OL  style="margin-left:80px;">
				<%   
				   for(int i =0;i<files.size();i++){
						ResourceFile bean = (ResourceFile)files.get(i);
						urls = "|" + bean.getSourceid()  ; 
				%>
				<li title="resource_<%=bean.getSourceid()%> "><%=bean.getUrl()%></li>
				<% } %>
			</OL>
			<br/>&nbsp;&nbsp;&nbsp; 	
	</div>
</div>
<%  // type =1 ID返回 |1088|1099?type=1
	// type =2 ID+URL返回 |1088=upload/1.jpg|1099=upload/2.jpg?type=2
	// type =3 URL返回 |upload/1.jpg|upload/2.jpg?type=2
	urls += "?type="+type+"&pack="+pack;
%> 
<%=urls%>${param.img}
<input type ="button" value="确定" onClick="win_uploded(); "  class="bt3"/>
</body>
</html> 
<script language="javascript"> //方法
<!-- 
 //设置父窗口的值 
function setFather() { 
 	father.document.getElementById(father_key).src = document.getElementById("intro").value 
} 
function win_uploded(){
	parent.document.getElementById("${param.img}").src = document.getElementById("intro").value  ;
	win_close();
}
function win_close(){
	parent.uploadwin_close();
}
//--> 
</script>
