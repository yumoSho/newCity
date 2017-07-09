<%@ page contentType="text/html; charset=GBK" pageEncoding="GBK" %>
<%@ include file="/common/inc/common.jsp"  %>
<style type="text/css"> 
body{
	font-size: 12px; 	
	scrollbar-arrow-color: #407DCA;
	scrollbar-3dlight-color: #ffffff;
	scrollbar-darkshadow-color: #E9EDF8;
	scrollbar-face-color: #DCE7F5;
	scrollbar-highlight-color: #DCE6F4;
	scrollbar-shadow-color: #efefef;
	scrollbar-track-color: #fcfcfc;
	overflow-x:hidden;
	margin: 0px;
	padding: 0px; background-color:#ffffff;
}
A:link {
	COLOR:#666666; TEXT-DECORATION: none
}
A:visited {
	COLOR:#666666; TEXT-DECORATION: none
}
A:hover {
	COLOR:red; TEXT-DECORATION: none
}
A:active {
	COLOR:#666666
}  
</style>
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
function setFile(fileId,fileUrl){
	var id = fileId;
	var url = fileUrl;
	var params = "?type=1&pack=id";
	var value = "|"+id+params;
	parent.window.next = 2;  // 设置 
	parent.window.returnValue = value;
	window.close();
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
<html>
<body>
<table width="99%" border="0" cellpadding="0" cellspacing="0" style="font-size:12px;" > 
	<% int i = 0; %>
 	<src:file  
		   order="ADD_DATETIME"  
		   uid="<%=UserSession.getUID(request)%>"	
	> 
    <tr class="table-tr2" onMouseOut="this.className='table-tr2';" onMouseOver="this.className='table-tr3';">
      <td height="22" width="11%" align="left"  ><input 
			type="checkbox" 
			name="selected_id" 
			value="${link.sourceid}" 
			onClick="check_(this)" /> <%=++i%></td> 
	  <td width="10%" align="center" class="table-cell"><span 
	  		style="color:#cccccc"><%=DateUtil.fmtYYMMDD(file.getAddDatetime()+"")%></span></td>  		 
      <td align="left" 	 class="table-cell">&nbsp;<a 
	  	href="javascript:setFile(${file.sourceid},'${file.url}')"><span>${file.name}</span></a></td>
      <td align="center" class="table-cell">  
		<a href="${path}/delsourcefile.do?sourceid=${file.sourceid}" onClick="return confirm('确认删除？')">删除</a> <a href="javascript:setFile(${file.sourceid},'${file.url}')">选择</a> 
	  </td>
    </tr>
	<tr>
		<td height="1" colspan="4" bgcolor="#EBEBEB"></td>
	</tr>
    </src:file>  
</table>
</body>
</html>
 