<%--
  ~ Copyright (c) 2014-2016. JarkimZhu
  ~ This software can not be used privately without permission
  --%>

<%--
  Created on 2016/8/15
  User: JarkimZhu
--%>

<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.igrowface.modules.payment.entity.dto.FinanceTransferDto" %>
<%@ page import="com.igrowface.libs.utils.DateUtils" %>
<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>转账申请</title>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="Cache-Control" content="no-cache; must-revalidate">
    <script type="text/javascript" src="js/jquery/jquery-2.1.3.min.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.cookie.js"></script>
    <script type="text/javascript" src="js/sdk/SdkConfigs.js"></script>
    <script type="text/javascript" src="js/sdk/ProtocolResult.js"></script>
    <script type="text/javascript" src="js/http/HttpUtils.js"></script>
    <script type="text/javascript" src="js/sdk/user/BaseUserManager.js"></script>
    <script type="text/javascript" src="js/sdk/user/alipay/AlipayUserManager.js"></script>
    <script type="text/javascript" src="js/sdk/pay/BasePayManager.js"></script>
    <script type="text/javascript" src="js/sdk/pay/alipay/AlipayPayManager.js"></script>
    <script type="text/javascript" src="js/sdk/SdkManager.js"></script>
    <script type="text/javascript">
        var global_base_url = "http://localhost:8080/web-domain";
        var sdkManager = new SdkManager();
        $(document).ready(function(){
            $("#fmTransfer").submit(function(event){
                event.preventDefault();
                var details = [];
                $("td[data-id='transferId']").each(function() {
                    details.push($(this).text());
                });

                sdkManager.transfer(SdkType.ALIPAY, details, function(err, ret){
                    if(err) {

                    }
                })
            });
        })
    </script>
</head>
<body>
<form action="pro/transfer/queryToTransfers" method="post">
    <table style="width: 100%">
        <tr>
            <td>起始时间</td>
            <td><input type="text" name="startDate" value="<%=DateUtils.getString((Date)request.getAttribute("startDate"))%>"/></td>
            <td>结束时间</td>
            <td><input type="text" name="endDate" value="<%=DateUtils.getString((Date)request.getAttribute("endDate"))%>"/></td>
        </tr>
        <tr>
            <td colspan="4" align="center"><input type="submit" value="查询"/></td>
        </tr>
    </table>
</form>
<%
    if(request.getAttribute("details") != null) {
%>
<form id="fmTransfer" action="/pro/finance/transfer" method="post">
    <%
        Map details = (Map) request.getAttribute("details");
        List alipayDetails = (List) details.get((short)1); //支付宝转账申请
        if(alipayDetails != null) {
    %>
    <table style="width: 100%">
        <tr>
            <th>提现申请号</th>
            <th>申请人</th>
            <th>申请账号</th>
            <th>提现金额</th>
            <th>申请时间</th>
        </tr>
        <%
            for(Object obj : alipayDetails) {
                FinanceTransferDto detail = (FinanceTransferDto) obj;
                pageContext.setAttribute("detail", detail);
        %>
        <tr>
            <td data-id="transferId">${detail.transferId}</td>
            <td data-id="accountName">${detail.transferAccount.accountName}</td>
            <td data-id="financeAccount">${detail.transferAccount.financeAccount}</td>
            <td data-id="amount">${detail.amount}</td>
            <td>${detail.createTimestamp}</td>
        </tr>
        <%
            }
        %>
    </table>
    <%
        } else {
    %>
    <div>没有查询到支付宝转账申请</div>
    <%
        }
    %>
    <div align="center"><input type="submit" value="确定提现"/></div>
</form>
<%
    }
%>
</body>
</html>
