<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.1.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Jquery.Query.js"></script>
<script type="text/javascript">
$(function(){
	var imgsrc=$.query.get('imgsrc');
	$("#img1").attr('src',imgsrc);
});
</script>
<body>
	<img id="img1" alt="" src="">
</body>
</html>