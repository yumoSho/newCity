<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ include file="/common/taglibs.jspf" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Slick Login</title>
    <meta name="description" content="NewCity Login">
    <link rel="stylesheet" type="text/css" href="${path}/css/admin/login.css" />
    <script type="text/javascript" src="${path}/js/jquery.min.js"></script>
    <script type="text/javascript">
        var path = '${path}';
    </script>
    <script type="text/javascript" src="${path}/js/login/login.js"></script>
</head>

<body onkeydown="keyClick()">
<form id="slick-login">
    <label >username</label><input type="text" name="username" id="username" class="placeholder" placeholder="请输入用户名">
    <label >password</label><input type="password" name="password" id="password" class="placeholder" placeholder="请输入密码">
    <input id="login" type="button" value="登  陆" onClick="adminLogin()">
    <script>
       function keyClick() {
           if(event.keyCode == 13){$("#login").click();}
       }
    </script>
</form>
</body>

</html>