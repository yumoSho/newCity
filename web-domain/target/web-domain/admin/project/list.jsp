<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/inc/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${app.name}</title> 
<%@ include file="/common/inc/meta.jsp" %>  
<script language="javascript" src="${path}/js/common/localedit.js"></script>
</head>
<body style="overflow:hidden; padding:2px;"> 
<div class="query-panel"  >
	<form id="queryForm" name="queryForm" action="#" style="margin:5px;"> 
		<input type="hidden" id="state" name="state" value="1">
		
		
		手机号码   <input type="text" id="mobile" name="mobile" />   
		
		 <select id="sex" name="sex">
				<option value="0">性别</option>
				<option value="1">男</option>
				<option value="2">女</option>
				
			</select>
	 <input type="button" value=" 搜 索 "  onclick="query()"/>
	</form> 
</div>   
<div class="l-loading" style="display:block" id="pageloading"></div>  
 	<div class="l-clear"></div> 
    <div id="maingrid"></div>  
  	<div style="display:none;"></div> 
</body>
</html>
<script language="javascript">

	$(document).ready(function(){
		query();
	}); 
	
	var pageSize = 20;
	var keyColumn = "accountId";
	var grid = $("#maingrid").ligerGrid({
			width: '99%', height: '99%', 
			rownumbers:true, 
			sortName: 'accountId', 
			pageSize: pageSize ,     
			columns: [  
				{ display: 'ID'   , name: 'accountId' , width: 360 , align: 'left'  },  
				{ display: '类型'  , name: 'thirdType', width: 60 , render: function(){
					return "普通";
				} },
				{ display: '账户'  , name: 'mobile', width: 90  , render: function( row , index , value){
					var html = "";
					return "<a href=\"javascript:edit('"+  row.accountId +"');\" >" + value +  "</a>";
				}}, 
				{ display: '性别'  , name: 'sex', width: 90  , render: function( row , index , value){
					var html = "-";
					if( value == 1 ){
						return "<span class='blue'>boy</span>";
					}
					if( value == 2){
						return "<span class='blue'>girl</span>";
					}
					return html;
				}}, 
				{ display: '昵称'  , name: 'nickName', width: 90 },
				{ display: '渠道' , name: 'channel', width: 130 },
				{ display: '第三方标识'  , name: 'thirdId', minWidth: 140 },   
				{ display: '注册时间'    , name: 'createtimestamp', width: 200 },
				{ display: '状态'    , name: 'loginState', width: 60 , render: RenderState} , 
				{ display: '操作'    , name: 'loginState', width: 60 , render: RenderOPBase} 
			]  ,
			toolbar: {
				items: [ 
				]
			},
			checkbox: true ,
	}); 
			  
	
	function query(){    
		QueryHtml( "${pro}/person/query" , QueryParam() );  
        $("#pageloading").hide();   
	}
  
	function add(){
		alert( 'Beta2.0 需求!' );
		//AddWin('${pro}/sysadmin/account/PersonAccount/add.jsp'  , 800, 375 );
	}

	function edit(id){
		EditWin('${pro}/sysadmin/account/PersonAccount/main.jsp'  , 1000, 660 , id ); 
	}
	
	function del( keys ){ 
		DelExc('${pro}/personAccount/del' , keys ); 
	}
	
	function setState( keys , state){
 		//alert( JSON.stringify(arguments) );
		Execute('${pro}/personAccount/setState' , {keys:keys, state:state} ); 
	}
	   
</script> 