<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

var tree_system = [
<% if(isAdmin ){%>
{ text: '系统管理',isexpand:true , children: [

{url:"${pro}/sysadmin/sys/SysModule/list.jsp",text:"模块/栏目/菜单"} ,
{url:"${pro}/sysadmin/sys/SysRole/list.jsp",text:"角色"} ,
{url:"${pro}/sysadmin/sys/SysAccount/list.jsp",text:"管理员"}

//, {url:"${pro}/sysadmin/sys/SysAction/list.jsp",text:"资源.Action"}
//, {url:"${pro}/sysadmin/sys/SysActiongroup/list.jsp",text:"资源.功能"}
//, {url:"${pro}/sysadmin/sys/SysPermission/modulePermission.jsp",text:"资源.菜单Bind"}

//, {url:"${pro}/sysadmin/sys/SysActiongroupResource/list.jsp",text:"Bind-ActionResource"}
//, {url:"${pro}/sysadmin/sys/SysActiongroupResource/list.jsp",text:"Bind-MosulePermission"}
]},

{ text: '数据库字典',isexpand:true , children: [
{url:"${pro}/sysadmin/welcome/deving.jsp",text:"词典管理"},
{url:"${pro}/sysadmin/welcome/deving.jsp",text:"省份编码"},
{url:"${pro}/sysadmin/welcome/deving.jsp",text:"城市编码"},
{url:"${pro}/sysadmin/welcome/deving.jsp",text:"县/区编码"},
{url:"${pro}/sysadmin/welcome/deving.jsp",text:"商圈编码"},
{url:"${pro}/sysadmin/welcome/deving.jsp",text:"学校编码"}
]},

{ text: '敏感词',isexpand:true, children: [
{url:"${pro}/sysadmin/welcome/deving.jsp",text:"拦截规则"},
{url:"${pro}/sysadmin/welcome/deving.jsp",text:"替换规则"}
]} ,


{ text: '资源管理',isexpand:true  , children: [
{url:"${pro}/sysadmin/welcome/deving.jsp",text:"图片资源"},
{url:"${pro}/sysadmin/welcome/deving.jsp",text:"视频资源"},
{url:"${pro}/sysadmin/welcome/deving.jsp",text:"音频资源"},
{url:"${pro}/sysadmin/welcome/deving.jsp",text:"文件资源"}
] } ,

{ text: '短信验证管理',isexpand:true , children: [
{url:"${pro}/sysadmin/welcome/deving.jsp",text:"已发送的"},
{url:"${pro}/sysadmin/welcome/deving.jsp",text:"接收到的"},
{url:"${pro}/sysadmin/welcome/deving.jsp",text:"发送失败的"}
]}
<% } %>
];

var tree_account = [
<% if(isAdmin ){ %>

{ text:"商家" , isexpand: true, children:[
{url:"${pro}/sysadmin/company/CompanyInfoModel/manage/list.jsp",text:"账户管理"} ,
{url:"${pro}/sysadmin/company/Certificate/company_list.jsp?certification=0",text:"未认证商家"} ,
{url:"${pro}/sysadmin/company/Certificate/company_list.jsp?certification=1",text:"待认证商家"} ,
{url:"${pro}/sysadmin/company/Certificate/company_list.jsp?certification=2",text:"已认证商家"}
]} ,

{ text:"用户" , isexpand: true , children:[
{url:"${pro}/sysadmin/account/PersonAccount/list.jsp", text:"账户"} ,
{url:"${pro}/sysadmin/account/PersonCertification/list.jsp?certification=1", text:"待认证用户"} ,
{url:"${pro}/sysadmin/account/PersonCertification/list.jsp?certification=2", text:"已认证"} ,
{url:"${pro}/sysadmin/account/PersonCertification/list.jsp?certification=3", text:"驳回"}
]}
<% } %>
];

var tree_operation = [
{ text:"兼职" , isexpand: true , children:[
{url:"${pro}/sysadmin/position/PositionMainModel/approve/list.jsp",text:"职位审核" } ,
{url:"${pro}/sysadmin/position/PositionMainModel/manage/list.jsp" ,text:"职位管理" }
]},
{ text:"活动" , isexpand: true , children:[
{text:"众筹"   ,isexpand:true  , url:"${pro}/sysadmin/activity/Crowd/list.jsp" } ,
{text:"线下"   ,isexpand:true  , url:"${pro}/sysadmin/activity/Offline/list.jsp" },
{text:"投票"   ,isexpand:true  , url:"${pro}/sysadmin/activity/Online/vote/list.jsp" }
]},
{ text:"订单" , isexpand: true , children:[
{text:"众筹订单"   ,isexpand:true  , url:"${pro}/sysadmin/order/OrderMain/list.jsp?bizType=1" } ,
{text:"兼职工资"   ,isexpand:true  , url:"${pro}/sysadmin/order/OrderMain/list.jsp?bizType=0" }
]},
{ text:"评价" , isexpand: true , children:[
{url:"${pro}/sysadmin/appraise/AppraiseCommon/list.jsp?type=1",text:"商家收到的评价" } ,
{url:"${pro}/sysadmin/appraise/AppraiseCommon/list.jsp?type=2",text:"用户收到的评价" }
]},
{ text:"投诉/意见反馈管理" , isexpand: true , children:[
{url:"${pro}/sysadmin/complain/OpinionCommon/list.jsp",text:"意见反馈" } ,

{url:"${pro}/sysadmin/complain/ComplainCommon/list.jsp",text:"兼职投诉" } ,
{url:"${pro}/sysadmin/complain/ComplainActivity/list.jsp",text:"活动反馈" }

]},
{ text:"收藏" , isexpand: true , children:[
{ text:"商家关注" ,isexpand:true ,url:"${pro}/sysadmin/history/PersonHistroy/list.jsp?type=1"  },
{ text:"职位收藏" ,isexpand:true ,url:"${pro}/sysadmin/history/PersonHistroy/list.jsp?type=2"  }
]},
{ text:"广告" , isexpand: true , children:[
{ text:"引导页"   	    ,isexpand:true  , url:"${pro}/sysadmin/ad/AdResource/view_full_single.jsp"}   ,
{ text:"首页Banner"     ,isexpand:true  , url:"${pro}/sysadmin/ad/AdResource/view_banner.jsp"} ,
{ text:"首页.专题1"     ,isexpand:true  , url:"${pro}/sysadmin/ad/AdResource/view_special.jsp?adId=4"} ,
{ text:"首页.专题2"     ,isexpand:true  , url:"${pro}/sysadmin/ad/AdResource/view_special.jsp?adId=5"}

]},
{ text:"推送" , isexpand: true , children:[
{ text:"GrowFace通知"   ,isexpand:true  , url:"${pro}/sysadmin/message/GrowFace/list.jsp" }
]},
{ text:"支付" , isexpand: true , children:[
{ text:"充值记录"       ,isexpand:true  , url:"${pro}/sysadmin/payment/payment_addrecord/list.jsp"}   ,
{ text:"支付记录"     ,isexpand:true  , url:"${pro}/sysadmin/payment/payment_payrecord/list.jsp"},
{ text:"提现记录"     ,isexpand:true  , url:"${pro}/sysadmin/payment/payment_cutrecord/list.jsp"}
]},
]

var tree_ad = [
]