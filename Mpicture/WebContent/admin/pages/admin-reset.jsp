<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

	<f:form action="${pageContext.request.contextPath}/admin/adminAdd" method="post" modelAttribute="admin">

		<f:input type="hidden" path="aid" value="${admin.aid}"/>
		<f:input type="hidden" path="username" value="${admin.username}"/>
		
		用户: ${admin.username}<br>
		密码：<input type="text" name="password" value=""/>
		
		<input type="submit" value="重置"/>
		
	</f:form>
	
</body>
</html>
