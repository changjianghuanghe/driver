<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>  
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

	<form action="${pageContext.request.contextPath}/admin/productAdd" method="post" enctype="multipart/form-data">

		小封面：<input type="file" name="cover" size="12" required="required"/><br>
		名称：<input type="text" name="name" placeholder="商品名称" required="required"/><br>
		价格：<input type="text" name="price" placeholder="价格支持小数" required="required"/><br>
		类型：<select name="type">
				<option value="1">半身头像</option>
				<option value="2">情侣合照</option>
			</select><br>
		大封面：<input type="file" name="bighead" size="12" required="required"/>
		<input type="submit" value="添加"/>
		
	</form>
</body>
</html>
