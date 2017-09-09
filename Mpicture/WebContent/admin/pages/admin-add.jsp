<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

	<f:form action="${pageContext.request.contextPath}/admin/adminAdd" method="post" modelAttribute="admin">

		用户：<f:input type="text" path="username" required="required"/><br>
		密码：<f:input type="text" path="password" required="required"/>
		
		<input type="submit" value="添加"/>
		
	</f:form>
	${addstatus}
</body>
</html>
