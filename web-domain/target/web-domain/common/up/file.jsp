<%@ page contentType="text/html; charset=gb2312" language="java" errorPage="" %>
<%@ page import="com.jinzhiit.UserSession"%>
<%@ page import="com.commons.framework.utility.StrUtil" %>
<% 
/**
 * Upload pic api 
 * Beta 4.1	
 * @author chenhui
 * @date 2010-05-01 
 * @local 西安
 * 带user 图片列表支持
 *
 */	
 
	String path = request.getContextPath(); 
	String local = request.getContextPath()+"/common/up";

	String name = request.getParameter("name");		//父页面：input name
	String savedir = request.getParameter("dir");
	String saveActionParm = "";
	if(!StrUtil.isNull(savedir)){ 
 		saveActionParm = "savedir="+savedir;
    }  
 
	boolean islogin = UserSession.isLogin(request);
	islogin = true;
%>

	
<%	if(!islogin){ %>
<table style="width:100%">
	<tr>
		<td  height="60"></td>
	</tr>
	<tr>
		<td align="center" style="color:#FF0000" >对不起您还没有登录！！</td>
	</tr>
</table>
<%		
		request.getRequestDispatcher("/common/up/file_login.jsp").forward(request,response);
		return;
	}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base target="_self" />
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title> Upload pic api - 4.1 <%=name%> | ${param.dir} </title>
<style type="text/css">
body{font-size:12px; margin:0; padding:0; width:540 color:#666666;background-color:#EFF4FA; 
}
li{ list-style:none; margin:0; text-align:left;}
.upcenter{ margin:0; padding:0;border-top:1px;border-top-color:#025296;border-top-style:solid; }  
.top{width:100%; padding-top:2px; padding-bottom:4px; color:#FFFFFF; background-color:#59A0CF; 
	border-top:1px;border-top-color:#009999;border-top-style:solid; text-align:left;
	border-bottom:1px;border-bottom-color:#399AD0;border-bottom-style:solid; }
.body{ width:100%; margin:0; padding:0; background-image:url(<%=local%>/img/bgq.jpg); background-repeat:repeat-x;
	border-top:1px;border-top-color:#ffffff;border-top-style:solid;padding-top:8px;
	border-bottom:1px; border-bottom-color:#ffffff; border-bottom-style:solid;}  
.footer{ background-color:#E5EEF6; height:33px; line-height:33px; padding-top:8px;
	 border-top:1px; border-top-color:#C4D7E6; border-top-style:solid;}

#user_photo{width:430px; margin:1px; border:1px; border-color:#7DB3D7; border-style:solid;background-color:#EEF3F9;}
#user_photo .div1{height:22px; line-height:22px; margin-left:7px; }
#viewup_{float:left;margin-top:0px;width:102px; height:102px;border:1px;border-color:#CFDEE8;border-style:solid;} 
#viewup{ border:1px;border-color:#ffffff; border-style:solid;}

.inputname {border-right:#999999 1px solid;border-top:#999999 1px solid;border-left:#999999 1px solid;border-bottom:#999999 1px solid;width:300px;color: #646464;heigth:22px;background-color:#FFFFff;
}
.style01{border:1px; border-color:#808080; border-style:solid;}
.style02{border:1px; border-color:#e6e6e6; border-style:solid;}

.photo_div04{margin-top:0px; width:490px; height:115px; border:1px; border-color:#D4D0C8; border-style:solid;background-color:#D4D0C8}


.footer .bt3 {background-image: url(<%=local%>/img/oao_img_075.gif); width: 54px;border-top-style: none; border-right-style: none;border-left-style: none; heigth: 22px; border-bottom-style: none}
.footer .bt4 {background-image:url(<%=local%>/img/oao_img_026.gif);width:54px;color:#ffffff;border-top-style:none;border-right-style:none;border-left-style:none;heigth: 22px; border-bottom-style: none}

.input_file{border:1px; height:20px; width:380px;}
</style>
<script language="javascript">
<%
/**
 * JS 
 * next = 0 :初始化
 * next = 1 :上传
 * next = 2 :用户历史
 * next = 3 :网络转载
 */
%>	 
var next = 0;
function $(s){return document.getElementById(s);}
function URL(url){

	this.url = url;
	
	this.getParam = function(name){
		var params = new Array();	
		var urls = this.url.split('?');	
		if(urls.length<=1)return "";	
		var paramArr = urls[1].split("&");	
		for(var i=0;i<paramArr.length;i++){  		
		var temp = paramArr[i].split("=");		
		params[temp[0]] = temp[1];	
		}	
		var paramValue = params[name];
		return paramValue;
	}

}
function onreset1(){
	//alert(123);
	next=0;
	window.close();
} 
function subEit(){ 
	if(next==0){
		var file = document.getElementById("file1").value
		if(file==null||file==""){
			alert("请选择需要上传的文件！");
			return;
		}
		document.getElementById("loadImg").style.display = '';
		document.getElementById("upBtnImg").style.display = 'none';
		var theForm = document.upForm;
		theForm.submit();
		return;
	}
	
	
 
	
	if(next==2){
		window.close();
		return;
	}
	
	if(next==3){ 
		window.returnValue =$("outimgurl").value;
		window.close();
		return;
	}


}
 
</script> 
</head>
<body>
<center>
<form action="${path}/upload.file?<%=saveActionParm%>" method="post"  enctype="multipart/form-data" name="upForm" >

<div class="upcenter"> 
<div class="top">
	<span style="color:#FFFFFF; font-size:9pt">
		&nbsp;<img src="<%=local%>/img/open.gif"  /> 
		友情提示: 
	</span>   
	可以上传 
	<span style="color:#EEF3F9; font-size:14px"> rar|zip|doc|xls </span> 
	等类型大小不得超过800K .
</div> 
		
<div class="body">
<table cellpadding="0" cellspacing="0">
	<tr>
		<td class="body_left" width="473" height="135"  valign="top"  align="left">
		<div id="user_photo" >
			<div style="border:1px; border-color:#FFFFFF; border-style:solid;">
				<div class="div1" style=" color:#001C30;" >
					<span style="float:left;">&nbsp;上传管理</span>
					<img 
						id="loadImg" 
						style="float:right; margin-top:4px;  display:none;" 
						src="<%=local%>/img/loading2.gif" align="absmiddle">
				</div>
				<div style=" background-color:#FFFFFF;">
					<iframe  
						width="460" 
						height="80" 
						style="margin-left:7px;"
						src="${dominurl}/common/up/user_files.jsp?a=1&b=2" 
						frameborder="0" /></iframe>
				</div>
				<div class="div1" style="color:#0072C1" >点击这里使用历史文件	</div>
			</div>	
		</div> 
		</td> 
	</tr> 
</table>
		
		</td>
	</tr>
</table>
</div>
<div id="returnurl" align="left" style="color:#666666; text-align:center; width:96%; padding-left:3px; float:left; line-height:18px;">
 	<p style="margin:1; margin-left:16px;  padding:0; border:0;"><img src='img/checkbox_checked.jpg' align='absmiddle' />
	地址
	     <input  type="file" 
		 	id="file1"
			class="input_file"
			name="file1"  
			style="border:1; width:420px;"
		  /></p>
</div>
<div class="footer" align="right">
	<span style="font-size:9pt;; color:#86A8C1">Copyright &copy;<a 	
		style="text-decoration:none; color:#86A8C1"
		href="http://www.jinzhiit.com" target="_blank">http://www.jinzhiit.com</a> 金智信息技术有限公司 </span>
	<img src="img/queding.gif" id="upBtnImg" onClick="subEit()"  style="cursor:hand:" align="absmiddle" />
	<img src="img/quxiao.gif" id="reset" onClick="onreset1();" style="cursor:hand:" align="absmiddle"/>&nbsp;&nbsp;
</div>	 
</div> 
</form>
</center>
<script language="javascript" > //执行
<!-- 
var father=window.dialogArguments; 
var father_key = "";
//获得父窗口传递来的值 (防止用户点击关闭按钮)
if(father!=null){ 
  	//father_key = father.document.getElementById("file_key").value;   
	var fdocument = father.document ; 
  	var url = father.document.getElementById("<%=name%>").value  ;
	//alert(">>"+url);
	//document.getElementById("file1").value = "xxxxxxxxxxxxxxxxxxxx";
} 
window.returnValue = "";
//window.status = "no";
//-->
</script>
</body>
</html>

