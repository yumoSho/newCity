<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/inc/common.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/common/inc/meta.jsp" %>
</head>
<body class="main-form">

<div class="main_box mat15" style="height:505px;" >
	<div class="graybox" >

		<form action="${pro}/personAccount/update" name="xForm" id="xForm">
			<p style="width:100%; margin:0" >
			<table  class="xform_tab" width="560" cellspacing="3" style="float:left">
				<tr class="label_style" >
					<td class="labeltit"><span>订单ID</span></td>
					<td>
						<input type="text" id="orderId"/>
					</td>
				</tr>
				<tr class="label_style"  >
					<td class="labeltit"><span>交易批次号</span></td>
					<td>
						<input type="text" id="batchnum"/>
					</td>
				</tr>
				<tr class="label_style" >
					<td class="labeltit"><span>订单状态</span></td>
					<td>
						<input type="text" id="state"/>
					</td>
				</tr>
				<tr class="label_style" >
					<td class="labeltit"><span>金额</span></td>
					<td>
						<input type="text" id="money"/>
					</td>
				</tr>
				<tr class="label_style"  >
					<td class="labeltit"><span>订单类型</span></td>
					<td>
						<input type="text" id="type"/>
					</td>
				</tr>
				<tr class="label_style"  >
					<td class="labeltit" valign="top"><span>订单所属账户</span></td>
					<td valign="top" >
						<input type="text" id="fromAccount"/>
					</td>
				</tr>

				<tr class="label_style"  >
					<td class="labeltit" valign="top"><span>订单交易账户</span></td>
					<td valign="top" >
						<input type="text" id="toAccount"/>
					</td>
				</tr>


				<tr class="label_style"  >
					<td class="labeltit"><span>备注</span></td>
					<td>
						<input type="text" id="remark"/>
					</td>
				</tr>

				<tr class="label_style"  >
					<td class="labeltit"><span>创建信息</span></td>
					<td>
						<input type="text" id="addAccount"/>
					</td>
				</tr>
				<tr class="label_style" >
					<td class="labeltit"><span>最后编辑信息</span></td>
					<td>
						<input type="text" id="lastAccount"/>
					</td>
				</tr>

				<tr class="label_style" >
					<td class="labeltit"><span>业务类型</span></td>
					<td>
						<input type="text" id="bizType"/>
					</td>
				</tr>

				<tr class="label_style">
					<td class="labeltit"><span>&nbsp;</span></td>
					<td>&nbsp;

					</td>
				</tr>
			</table>
			</p>
		</form>
	</div>
</div>

</body>
</html>

<script language="javascript">
//RenderUpload();
$(document).ready(function(){
	initTitle('订单 Order detail '); 
	LoadEntity( "${pro}/order/get" , "orderId");
});

function initForm( obj ){
	//alert( obj.moduleId );
	console.log( "Load initForm:"+ JSON.stringify(obj) );
 	$("#orderId").html( obj.orderId );
	 
  
 	$("#state").html(RenderOrderState( obj.state ));
	$("#batchnum").html( obj.batchnum ); 
	$("#money").html( obj.money ); 
	$("#type").html(  RenderType(obj.type) ); 
	$("#fromAccount").html(  obj.fromAccount  + "<br/><span class='blue'>" + obj.fromPayment+"</span>"); 
	$("#toAccount").html( obj.toAccount + "<br/><span class='blue'>" + obj.toPayment + "</span>"); 
	$("#remark").html( obj.remark ); 
	$("#addAccount").html(  "<span class='blue'>["+obj.addTime + "]</span> " +  obj.addAccount ); 
	$("#lastAccount").html( "<span class='blue'>["+obj.lastTime + "]</span> " +  obj.lastAccount ); 
	$("#bizType").html( obj.bizType );  
}

function RenderType( value ){
	
	if( value == 0){
		return "普通";
	}
	if( value == 1){
		return "批次";
	}
	if( value == 2){
		return "项目筹款";
	}
	if( value == 4){
		return "项目结算";
	}
	if( value == 5){
		return "操作";
	}
	return "-";
}

function RenderAccountType( value ){
	if( value == 1){
		return "系统";
	}
	if( value == 2){
		return "个人账户";
	}
	if( value == 3){
		return "商家";
	}
	if( value == 4){
		return "商家账户";
	}
	if( value == 5){
		return "管理员";
	}
}
function RenderOrderState(  value  ){
	if( value == 0 ){
		return "草稿";
	}
	if( value == 1 ){
		return "提交";
	}
	if( value == 11 ){
		return "<span class='blue'>支付中</span>";
	}
	if( value == 20 ){
		return "<span class='green'>已支付</span>";
	}
	if( value == 21 ){
		return "支付失败";
	}
	if( value == 30 ){
		return "删除";
	} 
	return "";
}   

</script>