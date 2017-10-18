<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ include file="/common/taglibs.jspf" %>
<%@ page import="com.Newcity.libs.filter.constants.SessionKey" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <%@include file="/common/elibs.jspf" %>

</head>
<body>
<%--头部--%>
<%@ include file="/common/head.jspf"%>
<%--导航--%>
<%@ include file="/common/sidebar.jspf"%>
<div id="content">
    <%--面包屑--%>
    <div id="content-header">
        <div id="breadcrumb"> <a href="../index/index.html" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> 首页</a></div>
    </div>

    <div class="span12">
            <div class="widget-box">
                <div class="widget-title"> <span class="icon"> <i class="icon-info-sign"></i> </span>
                    <h5>部门添加</h5>
                </div>
                <div class="widget-content nopadding">
                    <form class="form-horizontal" method="post" action="#" name="basic_validate" id="basic_validate" novalidate="novalidate">
                        <div class="control-group">
                            <label class="control-label">部门名称</label>
                            <div class="controls">
                                <input type="text" name="departmentName" id="departmentName" >
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">部门代码</label>
                            <div class="controls">
                                <input type="text" name="departmentKey" id="departmentKey" >
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">部门简介</label>
                            <div class="controls">
                                <textarea class="span11" style="width: 500px;" name="departmentInfo" title="" id="departmentInfo"></textarea>
                            </div>
                        </div>
                        <div class="form-actions">
                            <input type="button" value="Validate" id="submit" class="btn btn-success" onclick="submitJSON()">
                        </div>
                    </form>
                </div>
            </div>
        </div>
</div>
<script type="text/javascript">
    $('#basic_validate').validate({
        "ignore" : ":hidden:not(.ni)",
        rules : {
            departmentName: {
                required: true,
                maxlength: 10
            },

            departmentKey: {
                required: true
            }
        },
        messages:{
            departmentName:{
                required: "部门名称不能为空",
                maxlength: "部门名称不能超过10个字符"
            },
            departmentKey:{
                required: "部门代码不能为空",
            },
        },
    });
    function submitJSON(){
        var data = {
            departmentName:$("#departmentName").val(),
            departmentKey:$("#departmentKey").val(),
            departmentInfo:$("#departmentInfo").val()
        }
        if($("#basic_validate").valid()){
            $.ajax({
                url: path +'/department/addInfo',
                type: 'post',
                contentType : 'application/json',
                processData : false,
                dataType: 'json',
                data: JSON.stringify(data),
                success: function(data) {
                    window.location.href= path + "/department/index";
                    console.log("success !");
                },
                traditional:true
            });
        }
    }

</script>

</body>
</html>
