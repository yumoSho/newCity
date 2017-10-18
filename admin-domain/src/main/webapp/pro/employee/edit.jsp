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
                    <h5>人员添加</h5>
                </div>
                <div class="widget-content nopadding">
                    <form class="form-horizontal" method="post" action="#" name="basic_validate" id="basic_validate" novalidate="novalidate">

                        <div class="control-group">
                            <input type="hidden" name="id" id="id" value="${entity.id}">
                            <label class="control-label"><span class="redStar">*</span>用户名：</label>
                            <div class="controls">
                                <input type="text" name="userLoginName" id="userLoginName" value="${entity.userLoginname}">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label"><span class="redStar">*</span>用户姓名：</label>
                            <div class="controls">
                                <input type="text" name="userName" id="userName" value="${entity.userName}">
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label"><span class="redStar">*</span>部门：</label>
                            <div class="controls">
                                <select name="departmentNumber" id="departmentNumber">
                                    <option value="0" userType="">请选择</option>
                                    <c:forEach items="${departmentList}" var="info">
                                        <option value="${info.departmentId}" userType="${info.departmentKey}"
                                                <c:if test="${entity.departmentNumber == info.departmentId}">selected="selected"</c:if>>
                                                ${info.departmentName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label"><span class="redStar">*</span>手机号：</label>
                            <div class="controls">
                                <input type="text" name="userPhone" title="" id="userPhone" value="${entity.userPhone}"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">工龄</label>
                            <div class="controls">
                                <input type="text" name="userJobAge" title="" id="userJobAge" value="${entity.userJobAge}"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">邮箱</label>
                            <div class="controls">
                                <input type="text" name="userEmail" title="" id="userEmail" value="${entity.userEmail}"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">家庭住址</label>
                            <div class="controls">
                                <input type="text" name="userAddress" title="" id="userAddress" value="${entity.userAddress}"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">状态</label>
                            <div class="controls">
                                <select name="state" id="state">
                                    <option value="0" <c:if test="${entity.state == 0}">selected="selected"</c:if>>在职</option>
                                    <option value="1" <c:if test="${entity.state == 1}">selected="selected"</c:if>>冻结</option>
                                    <option value="2" <c:if test="${entity.state == 2}">selected="selected"</c:if>>离职</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-actions">
                            <input type="button" value="提交" id="submit" class="btn btn-success" onclick="submitJSON()">
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
            userName: {
                required: true,
                maxlength: 10
            },
            userPhone: {
                required : true,
                minlength : 11,
                // 自定义方法：校验手机号在数据库中是否存在
                // checkPhoneExist : true,
            },
            userEmail: {
                //邮箱验证
            },
            departmentNumber: {
                required: true,
            },
            userLoginname : {
                required: true,
            }

        },
        messages:{
            userName: {
                required: "用户名不能为空",
                maxlength: "用户名不能超过10个字符"
            },
            userPhone: {
                required : "手机号不能为空",
                minlength : "手机号长度不正确",
                // 自定义方法：校验手机号在数据库中是否存在
                // checkPhoneExist : true,
            },
            userEmail: {
                //邮箱验证
            },
            departmentNumber:{
                maxlength: "请选择部门"
            },
            userLoginname : {
                required: "请输入用户登录名称",
            }
        },
    });

    function submitJSON(){
        var data = {
            id:$("#id").val(),
            userName:$("#userName").val(),
            departmentNumber:$("#departmentNumber option:selected").val(),
            userPhone:$("#userPhone").val(),
            userEmail:$("#userEmail").val(),
            userPhone:$("#userPhone").val(),
            userAddress:$("#userAddress").val(),
            userType:$("#departmentNumber option:selected").attr("userType"),
            state:$("#state").val(),
            userLoginName:$("#userLoginName").val(),
            userJobAge:$("#userJobAge").val()
        }
        if($("#basic_validate").valid()){
            $.ajax({
                url: path +'/employee/employee.update',
                type: 'post',
                contentType : 'application/json',
                processData : false,
                dataType: 'json',
                data: JSON.stringify(data),
                success: function(data) {
                    if(data.result != 0){
                        alert(data.message);
                    }else{
                        window.location.href= path + "/employee/index";
                        console.log("success !");
                    }
                },
                traditional:true
            });
        }
    }

</script>

</body>
</html>
