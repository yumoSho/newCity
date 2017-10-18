<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ include file="/common/taglibs.jspf" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>康达公园新城管理后台</title>
    <%@include file="/common/elibs.jspf" %>
</head>
<body>
<%--头部--%>
<%@ include file="/common/head.jspf"%>
<%--导航--%>
<%@ include file="/common/sidebar.jspf"%>
<%--页面主体--%>
<div id="content">
    <%--面包屑--%>
    <div id="content-header">
        <div id="breadcrumb"> <a href="../index/index.html" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> 首页</a></div>
    </div>
    <div class="listPage">
        <!-- Begin 操作条 -->
        <div class="operateBar">
            <ul>
                <%--<li>
                    <a class="operate-delete" href="javascript:void(0);" class="">
                        <s class="icon-delete"></s>删除
                    </a>
                </li>--%>
                <%--<li>
                    <a class="operate-add" href="add">
                        <s class="icon-add-green"></s>新增
                    </a>
                </li>--%>
            </ul>
        </div>
    </div>
    <table id="tb_department"></table>
    <div id="pagination"></div>
</div>

</body>
</html>

<script>
    var pathSize = 10;
    var pn = $('#sp_1_pagination').html() == null ? 1 : $('#sp_1_pagination').html();
    console.log(pathSize +"   "+ pn);
    var $g = $('#tb_department').jqGrid({
        url: 'queryList.do' ,
        datatype: 'json' ,
        caption:"部门列表",  //显示查询结果表格标题
        colNames: [
            'ID', '部门代码', '部门名称','创建时间','修改时间','操作'
        ]
        , colModel: [
            {name: 'departmentId', index: 'departmentId', template: "text",hidden: true }
            , {name: 'departmentKey', index: 'departmentKey', template: "text",width:200,sortable:false }
            , {name: 'departmentName', index: 'departmentName', template: "text",width:330,sortable:false}
            /*, {name: 'parentsId', index: 'parentsId', template: "text",width:200,sortable:false }
            , {name: 'operator', index: 'operator', template: "text",width:200,sortable:false }*/
            , {name: 'createTime', index: 'createTime', template: "date",width:200,sortable:false,search: false }
            , {name: 'modificationTime', index: 'modificationTime', template: "date",width:200,sortable:false,search: false, }
            , {
                align: 'center',
                sortable: false,
                search: false,
                formatter: function (cell, options, row) {
                    var userType = row.userType;
                    var id = row.departmentId;
                    var html = '<div class="operateBox">'
                        + '<img onclick="cuEdit(\'' +id + '\')" src="' + path + '/images/admin/icon-edit01.png" width="15" height="15" title="Edit" alt="Edit">';
                    return html + '</div>';
                }
                , width: 70
            }
            /*, {template: 'actions2',width:100}*/
        ],
        multiselect: true,
        rowNum  : pathSize,//页面显示的条数
        rowList : pathSize,//供选择的条数
        autowidth: true,
        shrinkToFit: true,
        pager: '#pagination',
        sortname: 'createdDate',
        height: 'auto',
        sortorder: 'desc',
        viewrecords:  true,  //显示记录数信息，如果这里设置为false,下面的不会显示 recordtext: "第{0}到{1}条, 共{2}条记录",
        // 默认显示为{0}-{1} 共{2}条 scroll: false, //滚动翻页，设置为true时翻页栏将不显示

        jsonReader:{  //server返回Json解析设定
            root:  "data",  //对于json中数据列表
            page:  "page",  //当前页码
            total:  "total", //总页数
            records:  "records", //总记录数
            repeatitems:  false,
        }
    })

    $('.operateBar .operate-delete').click(function () {
        var conf = 1;
        var ids = $g.jqGrid('getGridParam', 'selarrrow');
        var keys = new Array();
        for(var i = 0,length = ids.length;i < length;i++){
            var key = $g.jqGrid('getRowData',ids[i]);
            keys[i] = key.departmentId;
        }
        console.log(keys);
        debugger;
        1 > keys.length ? alert("您至少应该选择一行记录") : conf = 0;
        if(conf == 0){
            if(confirm("您确定要删除选择的"+keys.length+"条")){
                $.ajax({
                    url: 'delete',
                    type: 'post',
                    traditional: true,
                    data: { id: keys }
                }).done(function (data) {
                    var removed;
                    if (data.result == 0) {
                        removed = data.success || [];
                        $g.trigger("reloadGrid");
                        layer.alert('操作成功', {
                            skin: 'layer-ext-message' //样式类名
                            ,closeBtn:1
                            ,time:3000
                            ,title:'提示 [3秒后消失]'
                            ,shade: 0
                            ,offset:'rb'
                            ,btn:''
                        });
                    }else{
                        layer.alert(data.message, {
                            skin: 'layer-ext-message' //样式类名
                            ,closeBtn:1
                            ,time:3000
                            ,title:'提示 [3秒后消失]'
                            ,shade: 0
                            ,offset:'rb'
                            ,btn:''
                        });
                    }
                }).fail(function () {
                    layer.alert('操作失败', {
                        skin: 'layer-ext-message' //样式类名
                        ,closeBtn:1
                        ,time:3000
                        ,title:'提示 [3秒后消失]'
                        ,shade: 0
                        ,offset:'rb'
                        ,btn:''
                    });
                });
            }
        }
    });
</script>