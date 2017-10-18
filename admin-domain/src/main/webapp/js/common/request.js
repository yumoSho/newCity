/**
 * Created by Administrator on 2017/8/8.
 */
//初始化Table
function getTable(name,url){
    $('#'+name).bootstrapTable({
        url:  url,         //请求后台的URL（*）
        method: 'get',                      //请求方式（*）
        //toolbar: '#toolbar',                //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: false,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        queryParams: oTableInit.queryParams,//传递参数（*）
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                       //初始化加载第一页，默认第一页
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: true,
        showColumns: true,                  //是否显示所有的列
        showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
        showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                   //是否显示父子表
        rowStyle: function (row, index) {
            //这里有5个取值代表5中颜色['active', 'success', 'info', 'warning', 'danger'];
            var strclass = "";
            if (row.ORDER_STATUS == "待排产") {
                strclass = 'success';//还有一个active
            }
            else if (row.ORDER_STATUS == "已删除") {
                strclass = 'danger';
            }
            else {
                return {};
            }
            return { classes: strclass }
        },
        columns: [{
            checkbox: true
        }, {
            field: 'accountId',
            title: 'ID'
        }, {
            field: 'departmentKey',
            title: '部门代码'
        }, {
            field: 'departmentName',
            title: '部门名称'
        }, {
            field: 'parentsId',
            title: '父id'
        }, {
            field: 'operator',
            title: '操作人'
        }, {
            field: 'createTime',
            title: '创建时间'
        }, {
            field: 'modificationTime',
            title: '修改时间'
        }, {
            field: 'loginState',
            title: '状态'
        }, {
            field: 'loginState',
            title: '操作'
        },]
    });
}

function customEdit(obj) {
    var $tr = $(obj).closest("tr.jqgrow")
        var rid = $tr.attr("departmentId"),
        visible = $('#datagrid').jqGrid('getRowData', rid).visible,
        $id = $(obj).closest("table.ui-jqgrid-btable").attr('departmentId').replace(/_frozen([^_]*)$/, '$1'),
        $grid = $("#" + $id),
        op = {
            extraparam: {}

        };
        window.location.href = "edit/" + rid;
}
function cuEdit(obj){
    /*var json = {
        id:obj
    };
    $.ajax({
        url: path +'/department/edit',
        type: 'post',
        contentType : 'application/json',
        processData : false,
        dataType: 'json',
        data: JSON.stringify(json),
        success: function(data) {

        },
        traditional:true
    });*/
    window.location.href = "edit/" + obj;
}

/**
 * 通过cookie获取参数
 * @param cookieName
 * @returns {*}
 */
function getCookie(cookieName){
    var cookieArray = document.cookie.split(";");
    for(var i = 0;i<cookieArray.length;i++){
        var keyValue = cookieArray[i].split("=");
        if(keyValue[0].trim() == cookieName){
            return keyValue[1];
        }
    }
}

// 手机号码验证
jQuery.validator.addMethod("isMobile", function(value, element) {
    var length = value.length;
    var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
    return this.optional(element) || (length == 11 && mobile.test(value));
}, "请正确填写您的手机号码");

jQuery.validator.addMethod("isEmail", function(value, element) {
    var length = value.length;
    var email = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    return this.optional(element) || (length == 11 && email.test(value));
},"请正确填写您的邮箱");

//设置cookie参数
var Cookie=new Object();
Cookie.setCookie=function(name, value, option){
    var str=name+'='+escape(value);
    if(option){
        if(option.expireHours){
            var d=new Date();
            d.setTime(d.getTime()+option.expireHours*3600*1000);
            str+='; expires='+d.toString();
        }
        if(option.path) str+='; path='+option.path;
        if(option.domain) str+='; domain='+option.domain;
        if(option.secure) str+='; true';
    }
    document.cookie=str;
}

//删除Cookies
Cookie.delCookie=function(name){
    debugger;
    var myDate=new Date();
    myDate.setTime(-1000);//设置时间
    document.cookie=name+"=''; expires="+myDate.toGMTString();
}