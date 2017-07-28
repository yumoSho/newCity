<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ include file="/common/taglibs.jspf" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>error</title>
</head>
<body>
    <c:if test="${code == 0}">
        <div>${errorMessage}</div>
        <a href="${path}/admin/login.html">跳转登陆页面</a>
    </c:if>
</body>
</html>