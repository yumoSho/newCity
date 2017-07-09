<%@ page contentType="text/html; charset=utf-8" language="java"   %>
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
	pageContext.setAttribute("path"  , path );
	pageContext.setAttribute("local" , local);

	String name = request.getParameter("name");		//父页面：input name
	String view = request.getParameter("view");		//父页面：显示图片ID

%>
<%
/*	boolean userLogin = UserSession.isLogin(request); 
	if(userLogin==false ){
	//if(false){

%>
<table style="width:100%">
	<tr>
		<td  height="60"></td>
	</tr>
	<tr>
		<td align="center" style="color:#FF0000" >对不起您还没有登录！！</td>
	</tr>
</table>
<%		

		request.setAttribute("name",name);
		request.setAttribute("view",view);
		request.getRequestDispatcher("/common/up/img_login.jsp").forward(request,response);
		return;
	}
	*/
%> 
<!-- Upload pic api - 4.1 <%=name%> | <%=view%>   -->
<style type="text/css"> 
img{ cursor:pointer}
html{ margin:0; padding:0;}
body{ margin:0; padding:0;}
.upcenter{border-top:1px;border-top-color:#025296;border-top-style:solid; margin:0; padding:0; }  

.uploadtop{ width:100%; padding-top:0px; padding-bottom:0px; color:#FFFFFF; background-color:#59A0CF;
			border-top:1px;border-top-color:#009999;border-top-style:solid; text-align:left;
			border-bottom:1px;border-bottom-color:#399AD0;border-bottom-style:solid; height:16px; font-size:12px; line-height:16px; }

.uploadbody{ width:100%; margin:0; padding:0; background-image:url(<%=local%>/img/bgq.jpg); background-repeat:repeat-x;
			 border-top:1px;border-top-color:#ffffff;border-top-style:solid;padding-top:0px;
			 border-bottom:1px; border-bottom-color:#ffffff; border-bottom-style:solid; }  
.body_right{ font-size:12px;}

.uploadfooter{ background-color:#E5EEF6; height:26px; line-height:26px; border-top:1px; border-top-color:#C4D7E6; border-top-style:solid;}
.returnurl_contro{ float:left; padding-top:1px;}
.returnurl_contro .ctro_bt{ border:1px #000000 solid; background-color:#666666; color:#FFFFFF; }
.returnurl{ color:#666666; float:right; padding-left:1px; font-size:12px; }

.file1{ width:360px;  margin-top:1px;  }
.outimgurl{border:1px #000000 solid;  width:298px ;  float:left;height:20px; line-height:20px;  margin-top:1px; } 
.upBtnImg{ width:60px; float:right; height:20px; margin-left:2px;  margin-top:1px; }



#user_photo{width:430px; margin:1px; border:1px; border-color:#7DB3D7; border-style:solid;background-color:#EEF3F9;}
#user_photo .div1{height:22px; line-height:22px; margin-left:7px; }  
.inputname{	border-right:#999999 1px solid;border-top:#999999 1px solid;border-left:#999999 1px solid;border-bottom:#999999 1px solid;width:300px;
				color: #646464;heigth:22px;background-color:#FFFFff;
		   } 
.photo_div04{margin-top:0px; width:490px; height:115px; border:1px; border-color:#D4D0C8; border-style:solid;background-color:#D4D0C8}






.uploadfooter .bt3 {background-image: url(<%=local%>/img/oao_img_075.gif); width: 54px;border-top-style: none; border-right-style: none;border-left-style: none; 
				heigth: 22px; border-bottom-style: none; cursor:pointer}
.uploadfooter .bt4 {background-image:url(<%=local%>/img/oao_img_026.gif);width:54px;color:#ffffff;border-top-style:none;border-right-style:none;border-left-style:none;
				heigth: 22px; border-bottom-style: none; cursor:pointer}
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

		
 
	function subEit(){ 
		if(next==0){
			window.close();
			return;
		} 
		
		if(next==1){ 
			var theForm = document.upForm;
			theForm.submit();
			return;
		} 
		
 
		if(next==3){ 
			parent.uploadwin_uploaded("${param.id}" , document.getElementById("outimgurl").value); 
			return;
		} 
	}


	
	function viewreturnurl(){
		 
	}
	
	function viewimg(url){  
	}
	

</script>
<form action="${pageContext.request.contextPath}/upload.img?id=${param.id}" method="post"  enctype="multipart/form-data" name="upForm" >

<div class="upcenter">
 
	<div class="uploadtop">
		<span style="color:#FFFFFF; font-size:12px; padding-left:5px;">  &copy; 126yun.com  </span>   
		可上传 <span style="color:#EEF3F9; font-size:12px"> gif,jpg,jpeg,png,bmp </span>  类型图片,单张大小不超过800K .
		<span style="float:right; margin-right:2px;"><img src="${local}/img/close.gif" onclick="win_close()"></span> 
	</div>
	
	<div class="uploadbody">
		<table cellpadding="0" cellspacing="0">
			<tr>
				<td class="body_left" width="433"  valign="top"  align="left">
					<div id="user_photo" >
						<div style="border:1px; border-color:#FFFFFF; border-style:solid;"> 
							<div style=" background-color:#FFFFFF; ">
								<iframe width="427" height="160" frameborder="0" style="margin:0; padding:0;" 
										src="${local}/user_imgs.jsp?a=1&b=2" 
								/></iframe>
							</div> 
						</div>	
					</div> 
				</td>
				<td class="body_right"  valign="top" style="display:none" >
					<table>  
						<tr>
							<td   align="center" >
							 
							</td>
						</tr> 
					</table>
				</td>
			</tr>
		</table>
	</div>
	
	
	
	<div class="uploadfooter" align="right"> 
		<span id="returnurl_contro" class="returnurl_contro" > <input type="button" class="ctro_bt" value="网络图片"  onClick="useOutImg()"  /> </span>
		<span id="returnurl" class="returnurl"  > 
			<input type="file" class="file1" id="file1" name="file1"  style=" border:1; float:right" onChange="onFileChange();"   /> 
		</span>
		
		
		<img src="<%=local%>/img/quxiao.gif"  id="reset" onClick="win_close();" style="cursor:pointer; margin-right:4px; display:none;" align="absmiddle"/>
		
	</div>
	 
</div>
<input type="hidden" name="name_key" id="name_key" />
</form>  
<script language="javascript"> //方法 
function onFileChange(){
	next = 1; 
	subEit();
	 
}

function win_close(){
	parent.uploadwin_close();
} 

function useOutImg(){
	next = 3; 
	//prompt('http://');
	var htmlStr = "";
	htmlStr += " <input type='text' class='outimgurl' id='outimgurl' name='outimgurl' value='http://'  ";
	//htmlStr += " onChange ='viewimg(this.value)'";
	htmlStr += " />";
	
	//src=\"${local}/img/queding.gif\
	htmlStr += " <input type='button'  "
	htmlStr += " class='upBtnImg' "
	htmlStr += " id=\"upBtnImg\" "; 
	htmlStr += " onClick=\"subEit()\"  "; 
	htmlStr += " value='确定' ";
	htmlStr += "  />";
	//alert(htmlStr);
	document.getElementById("returnurl").innerHTML = htmlStr;
	$("outimgurl").select();
	
	//$("outimgurl").focus();
}
</script>
