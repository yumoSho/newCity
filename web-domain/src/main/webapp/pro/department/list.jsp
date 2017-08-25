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
				{ display: 'ID'   , name: 'accountId' , width: 360 , align: 'left' ,hide : true  },
				{ display: '部门代码'  , name: 'departmentKey', width: 100 , render: function(){
					return "普通";
				} },
				{  display: '部门名称'  , name: 'departmentName', width: 100 },
				{  display: '父id'  , name: 'parentsId', width: 100 },
				{ display: '操作人' , name: 'operator', width: 130 },
                { display: '创建时间'  , name: 'createTime', width: 140 },
				{ display: '修改时间'  , name: 'modificationTime', minWidth: 140 },
				{ display: '状态'    , name: 'loginState', width: 60 , render: RenderState} ,
				{ display: '操作'    , name: 'loginState', width: 100 , render: RenderOPBase}
			]  ,
			toolbar: {
				items: [ 
				]
			},
			checkbox: true ,
	}); 
			  
	
	function query(){
		/*QueryHtml( "/person/query" , QueryParam() );  */
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