<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- 自适应配置 -->
<meta name="viewport"
	content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
<link rel="stylesheet" href="../css/stylees.css" />
<link rel="stylesheet" href="../css/zjmx.css" />
<!-- 自适应样式 -->
<link rel="stylesheet" href="../css/xitongyangshi.css" />
<!-- 动态获取的数据样式 -->
<link rel="stylesheet" href="../css/personalset.css" />

<title></title>

<style type="text/css">
/*图片或其他自适应*/
img {
	max-width: 100%;
}

body {
	padding: 50px 0 0 50px;
}

.datainp {
	width: 200px;
	height: 30px;
	border: 1px #ccc solid;
}

.datep {
	margin-bottom: 40px;
}
</style>
</head>
<script type="text/javascript" src="../js/jquery-3.1.1.js"></script>
<link rel="stylesheet"
	href="http://code.jquery.com/mobile/1.3.1/jquery.mobile-1.3.1.min.css" />
 <script src="http://code.jquery.com/jquery-1.9.1.min.js"></script> 
<script
	src="http://code.jquery.com/mobile/1.3.1/jquery.mobile-1.3.1.min.js"></script>

<!--Includes-->
<link href="../css/mobiscroll.custom-2.5.0.min.css" rel="stylesheet"
	type="text/css" />
<script src="../js/mobiscroll.custom-2.5.0.min.js"
	type="text/javascript"></script>
<!-- 自适应判断 -->
<script type="text/javascript" src="../js/Adaptive.js"></script>
<script type="text/javascript">
	$(function() {
		$('input:jqmData(role="datebox")').mobiscroll().date();
	});
</script>
<body>
	<div style="width: 100%; height: 100%;position: fixed;">
		<!-- 导航栏  -->
		<div class="todown">
			<div
				style="height: 3.5em; width: 100%; position: fixed; top: 0; background: #FFAC66;">
				<!-- 页眉内容 -->
				<div class="down">
					<div class="stylees-right">
						<a href="menu.html"><img src="img/right.png"
							style="width: 3%; height: 3%" /></a>
					</div>
					<div class="stylees-wanzi">个人设置</div>
					<br />
					<!-- 水平分割线 
		    <div id="level"></div>-->
				</div>
			</div>
		</div>

		<div style="text-align: center; margin: 20% 0">
			<form action="${pageContext.request.contextPath}/index/usersUpdate"
				method="post">
				<div data-role="fieldcontain">
					电话:<input type="text" name="phonenumber" /> 
					邮箱:<input type="text" name="email" />
					<label for="txtBirthday">出生日期：</label>
					<input type="text" data-role="datebox" id="txtBirthday" name="birth" />
					性别:<select name="sex">
						<option value="1">男</option>
						<option value="2">女</option>
					</select> 
					<br> <input type="submit" value="添加" />
				</div>
			</form>
		</div>

		<!-- 页脚内容 -->
		<div class="footdiv">
			<a href="../index/productHeadList?nowpage=1&type=1">
				<div style="position: fixed; width: 50%; left: 0;">
					<img src="img/dingzhiicon.png"
						style="width: 15%; height: 15%; margin-left: 5em;">
					<div style="margin-left: 6em; font-size: 0.8em; color: gray;">定制</div>
				</div>
			</a> <a href="menu.html">
				<div style="position: fixed; width: 50%; right: 0;">
					<img src="img/myicon.png"
						style="width: 15%; height: 15%; margin-left: 4.8em;">
					<div style="margin-left: 6em; font-size: 0.8em; color: gray;">我的</div>
				</div>
			</a>
		</div>
	</div>
</body>
</html>