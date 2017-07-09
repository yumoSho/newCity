
var project = role == "1"?[
    { text: '项目管理',isexpand:true , children: [
        {url:"${pro}/sysadmin/sys/SysModule/list.jsp",text:"项目管理"} ,
    ]}
]: "" ;


var tree_system = [
    project
];

var tree_account = [

	{ text:"财务管理" , isexpand: true, children:[
		{url:"${pro}/sysadmin/company/CompanyInfoModel/manage/list.jsp",text:"账户管理"} ,
		{url:"${pro}/sysadmin/company/Certificate/company_list.jsp?certification=0",text:"未认证商家"} ,
		{url:"${pro}/sysadmin/company/Certificate/company_list.jsp?certification=1",text:"待认证商家"} ,
		{url:"${pro}/sysadmin/company/Certificate/company_list.jsp?certification=2",text:"已认证商家"}
	]} ,

];

var tree_operation = [
	{ text:"兼职" , isexpand: true , children:[
		{url:"${pro}/sysadmin/position/PositionMainModel/approve/list.jsp",text:"职位审核" } ,
		{url:"${pro}/sysadmin/position/PositionMainModel/manage/list.jsp" ,text:"职位管理" }
    ]},
]

var tree_ad = [
] 