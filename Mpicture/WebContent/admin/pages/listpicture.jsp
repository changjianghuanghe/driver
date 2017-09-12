<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE1 {font-size: 12px}
.STYLE4 {
	font-size: 12px;
	color: #1F4A65;
	font-weight: bold;
}

a:link {
	font-size: 12px;
	color: #06482a;
	text-decoration: none;

}
a:visited {
	font-size: 12px;
	color: #06482a;
	text-decoration: none;
}
a:hover {
	font-size: 12px;
	color: #FF0000;
	text-decoration: underline;
}
a:active {
	font-size: 12px;
	color: #FF0000;
	text-decoration: none;
}

.pagelist span,.pagelist a{ border-radius:3px; border:1px solid #dfdfdf;display:inline-block; padding:5px 12px;}
-->
</style>
</head>

<body>

<table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30">
	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
		  <td width="15" height="30"><img src="images/tab_03.gif" width="15" height="30" /></td>
		  <td background="images/tab_05.gif"><img src="images/311.gif" width="16" height="16" /> 
			<span class="STYLE4">最新完成列表</span></td>
		  <td width="14"><img src="images/tab_07.gif" width="14" height="30" /></td>
		</tr>
	  </table>
	</td>
  </tr>
  <tr>
    <td>
	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="9" background="images/tab_12.gif">&nbsp;</td>
          <td bgcolor="e5f1d6">
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#CECECE">
				<tr>
					<td width="20%" height="36" background="images/tab_14.gif" class="STYLE1">
						<div align="center" class="STYLE2 STYLE1">图片</div></td>
					<td width="20%" height="36" background="images/tab_14.gif" class="STYLE1">
						<div align="center" class="STYLE2 STYLE1">操作</div></td>
					<!-- <td width="16%" height="36" background="images/tab_14.gif" class="STYLE1">
						<div align="center" class="STYLE2 STYLE1"></div></td>
					<td width="16%" height="36" background="images/tab_14.gif" class="STYLE1">
						<div align="center" class="STYLE2 STYLE1"></div></td>
					<td width="16%" height="36" background="images/tab_14.gif" class="STYLE1">
						<div align="center" class="STYLE2 STYLE1"></div></td> -->
					<td width="60%" style="background-color: white;">
						<div align="center" ></div></td>
				</tr>
				<c:forEach items="${pictures}" var="picture" varStatus="status">
					<tr>
						<td height="20" bgcolor="#FFFFFF">
							<div align="center" class="STYLE2 STYLE1"><img onclick="getbighead(this)" style="width: 38%;height: 38%;border-radius: 50%;" src="${picture }"> </div></td>
						
						<td height="20" bgcolor="#FFFFFF">
							<div align="center" class="STYLE2 STYLE1"><a href="${pageContext.request.contextPath}/admin/delpicture?picturename=${picture }">[删除]</a></div></td>
						
						<td width="60%" style="background-color: white;">
							<div align="center" ></div></td>
					</tr>
				</c:forEach>
	
				
				
			</table>
		  </td>
		  <td width="9" background="images/tab_16.gif">&nbsp;</td>
		</tr>
	  </table>
	</td>
  </tr>
  <tr>
    <td height="29">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		  <tr>
			<td width="15" height="29"><img src="images/tab_20.gif" width="15" height="29" /></td>
			<td background="images/tab_21.gif"></td>
			<td width="14"><img src="images/tab_22.gif" width="14" height="29" /></td>
		  </tr>
		</table>
	</td>
  </tr>
</table>

<c:if test="${fn:length(pictures)<5}">
	<form action="${pageContext.request.contextPath}/admin/addpicture" method="post" enctype="multipart/form-data">
		<input type="file" name="picture"/>
		<button>添加</button>
	</form>
</c:if>

		
</body>
<script type="text/javascript">

function action(){
	var s=confirm("是否确定删除？"); 
	if(s==false){
		return false;
	}
}


function getbighead(img){
	window.open("${pageContext.request.contextPath}/admin/pages/seeBigHead.jsp?imgsrc="+img.src);
}
</script>
</html>
