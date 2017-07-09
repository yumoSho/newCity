<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ page import="java.util.List" %>  
<%@ page import="com.commons.framework.config.RequestScope" %>
<%@ page import="com.yun.resource.file.bean.ResourceFile" %>
<%
   String path = request.getContextPath();
   String local = request.getContextPath()+"/common/up";
   List files = (List)request.getAttribute(RequestScope.UPLOAD_FILES); 
   if(files==null||files.size()<=0){
   		out.print("Img Upload Err");
		return;
   }
   int size = files.size();
   int type = 1;
   String pack = "id"; 
   String urls = "";
   
   String fileurl = "";
%>
<style>
html{ background-color:#FFFFFF}
</style>
<%  
	for(int i =0;i<files.size();i++){
		ResourceFile bean = (ResourceFile)files.get(i);
		fileurl = bean.getUrl()+"?photo="+bean.getSourceid()  ; 
	}	
%> 
${dominurl}/<%=fileurl %>
<input id="img_url" type="hidden" name="img_url" value="<%=fileurl %>" />
<input type ="button" value="ok" onClick="win_uploded(); " />
<script language="javascript">
function win_uploded(){
	//alert( '${param.id}' );
	parent.uploadwin_uploaded("${param.id}" , "<%=fileurl%>");
} 
win_uploded();
</script>
 
 