<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

var tree_financial = [
<%--<% if (role == "NC_FINANCE" || role == "NC_BOSS") {%>--%>
{ text: '财务管理',isexpand:true , children: [

{url:"${path}/sysadmin/sys/SysModule/list.jsp",text:"模块/栏目/菜单"} ,
{url:"${path}/sysadmin/sys/SysRole/list.jsp",text:"角色"} ,
{url:"${path}/sysadmin/sys/SysAccount/list.jsp",text:"管理员"}

//, {url:"${path}/sysadmin/sys/SysAction/list.jsp",text:"资源.Action"}
//, {url:"${path}/sysadmin/sys/SysActiongroup/list.jsp",text:"资源.功能"}
//, {url:"${path}/sysadmin/sys/SysPermission/modulePermission.jsp",text:"资源.菜单Bind"}

//, {url:"${path}/sysadmin/sys/SysActiongroupResource/list.jsp",text:"Bind-ActionResource"}
//, {url:"${path}/sysadmin/sys/SysActiongroupResource/list.jsp",text:"Bind-MosulePermission"}
]},
<%--<% } %>--%>
];



var tree_account = [
<%--<% if  (role == "NC_MARKET" || role == "NC_BOSS"){ %>--%>

{ text:"销售管理" , isexpand: true, children:[
{url:"${path}/sysadmin/company/CompanyInfoModel/manage/list.jsp",text:"账户管理"} ,
{url:"${path}/sysadmin/company/Certificate/company_list.jsp?certification=0",text:"未认证商家"} ,
{url:"${path}/sysadmin/company/Certificate/company_list.jsp?certification=1",text:"待认证商家"} ,
{url:"${path}/sysadmin/company/Certificate/company_list.jsp?certification=2",text:"已认证商家"}
]} ,

{ text:"用户" , isexpand: true , children:[
{url:"${path}/sysadmin/account/PersonAccount/list.jsp", text:"账户"} ,
{url:"${path}/sysadmin/account/PersonCertification/list.jsp?certification=1", text:"待认证用户"} ,
{url:"${path}/sysadmin/account/PersonCertification/list.jsp?certification=2", text:"已认证"} ,
{url:"${path}/sysadmin/account/PersonCertification/list.jsp?certification=3", text:"驳回"}
]}
<%--<% } %>--%>
];


var tree_personnel = [
<%--<% if  ( role == "NC_BOSS"){ %>--%>
{ text:"兼职" , isexpand: true , children:[
{url:"${path}/pro/department/list.jsp",text:"部门管理" } ,
{url:"${path}/pro/department/add.jsp" ,text:"部门添加" },
{url:"${path}/pro/department/edit.jsp" ,text:"部门编辑" }
]},
{ text:"活动" , isexpand: true , children:[
{text:"众筹" ,isexpand:true , url:"${path}/sysadmin/activity/Crowd/list.jsp" } ,
{text:"线下" ,isexpand:true , url:"${path}/sysadmin/activity/Offline/list.jsp" },
{text:"投票" ,isexpand:true , url:"${path}/sysadmin/activity/Online/vote/list.jsp" }
]},
{ text:"订单" , isexpand: true , children:[
{text:"众筹订单" ,isexpand:true , url:"${path}/sysadmin/order/OrderMain/list.jsp?bizType=1" } ,
{text:"兼职工资" ,isexpand:true , url:"${path}/sysadmin/order/OrderMain/list.jsp?bizType=0" }
]},
{ text:"评价" , isexpand: true , children:[
{url:"${path}/sysadmin/appraise/AppraiseCommon/list.jsp?type=1",text:"商家收到的评价" } ,
{url:"${path}/sysadmin/appraise/AppraiseCommon/list.jsp?type=2",text:"用户收到的评价" }
]},
{ text:"投诉/意见反馈管理" , isexpand: true , children:[
{url:"${path}/sysadmin/complain/OpinionCommon/list.jsp",text:"意见反馈" } ,

{url:"${path}/sysadmin/complain/ComplainCommon/list.jsp",text:"兼职投诉" } ,
{url:"${path}/sysadmin/complain/ComplainActivity/list.jsp",text:"活动反馈" }
]}
<%--<% } %>--%>
];



var tree_system = [
<%--<% if  ( role == "NC_BOSS" || role == "NC_ADMIN"){ %>--%>
<%--<%}%>--%>
]
