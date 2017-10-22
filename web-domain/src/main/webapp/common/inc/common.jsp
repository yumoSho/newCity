<%@ taglib uri="http://java.sun.com/jstl/core_rt"  prefix="c"%>
<%
	String path = request.getContextPath();
    request.setAttribute("ligerUI", request.getContextPath() + "/js/ligerUI-1.3.3");
	request.setAttribute("path",path);
%>
