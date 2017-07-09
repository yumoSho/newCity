<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/inc/common.jsp" %> 
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>信息提示</title> 
	<%@ include file="/common/inc/meta.jsp" %> 
	<style type="text/css">
				
		form {
			margin: 0;
		}
		textarea {
			display: block;
		}
	</style>
	
	<script type="text/javascript">
		function open()
		{
			window.location = "";
		}	
		
		function refop()
		{
			art.dialog.opener.location.reload();
		}		
	</script>
	
</head> 
<body class="cont" onLoad="refop();">
<div class="mainbox">
	<p class="gray lh30">提示：</p>
	<div class="document_top por">
		<div align="center">
			<font size="24" color="red"><strong>提交成功</strong></font>
			 &nbsp;&nbsp;&nbsp;&nbsp;
			<a href="#" onClick="closeWin();">关闭窗口</a>
		</div>
	</div>
</div>
</body>
</html>	