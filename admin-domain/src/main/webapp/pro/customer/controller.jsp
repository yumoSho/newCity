<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ include file="/common/taglibs.jspf" %>
<%@ page import="com.Newcity.libs.filter.constants.SessionKey" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>康达公园新城管理后台</title>
    <%@include file="/common/elibs.jspf" %>

</head>
<body>
<script>
    console.log(navigator.userAgent);
    if ((navigator.userAgent.match(/(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i))) {
        alert('手机端')
    }else{
        alert('PC端')
    }
</script>
</body>
</html>