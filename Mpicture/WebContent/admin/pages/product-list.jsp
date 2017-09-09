<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

-->
</style>
</head>

<body>

<table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30">
    <form action="${pageContext.request.contextPath}/admin/productSearch" id="serchform" method="post">
	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
		  <td width="15" height="30"><img src="images/tab_03.gif" width="15" height="30" /></td>
		  <td background="images/tab_05.gif"><img src="images/311.gif" width="16" height="16" /> 
			<span class="STYLE4">商品列表&nbsp;&nbsp;
				<!-- &nbsp;&nbsp;输入编号: <input type="text" name="product.id" id="serchform" size="3"/> -->
				&nbsp;&nbsp;输入名称: <input type="text" name="productname" id="serchform" size="3"/>
					<a href="javascript:;" onclick="document.getElementById('serchform').submit();">点击查询</a>
				</span></td>
		  <td width="14"><img src="images/tab_07.gif" width="14" height="30" /></td>
		</tr>
	  </table>
	</form>
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
					<td width="5%" height="26" background="images/tab_14.gif" class="STYLE1">
						<div align="center" class="STYLE2 STYLE1">编号</div></td>
					<td width="20%" height="26" background="images/tab_14.gif" class="STYLE1">
						<div align="center" class="STYLE2 STYLE1">图片</div></td>
					<td width="20%" height="26" background="images/tab_14.gif" class="STYLE1">
						<div align="center" class="STYLE2 STYLE1">名称</div></td>
					<td width="10%" height="26" background="images/tab_14.gif" class="STYLE1">
						<div align="center" class="STYLE2 STYLE1">价格</div></td>
					<td width="10%" height="26" background="images/tab_14.gif" class="STYLE1">
						<div align="center" class="STYLE2 STYLE1">类型</div></td>
					<td width="20%" height="26" background="images/tab_14.gif" class="STYLE1">
						<div align="center" class="STYLE2">操作</div></td>
				</tr>
				   
				<!-- 用struts的iterator标签遍历news集合, 并去处每一个name显示 -->
				<c:forEach items="${products}" var="product" varStatus="status">
					<tr>
						<td height="20" bgcolor="#FFFFFF">
							<div align="center" class="STYLE2 STYLE1">${product.pid }</div></td>
						<td height="20" bgcolor="#FFFFFF">
							<div align="center" class="STYLE2 STYLE1"><img style="width: 38%;height: 38%;border-radius: 50%;" src="${product.cover }" height="80px"></div></td>
						<td height="20" bgcolor="#FFFFFF">
							<div align="center" class="STYLE2 STYLE1">${product.name }</div></td>
						<td height="20" bgcolor="#FFFFFF">
							<div align="center" class="STYLE2 STYLE1">${product.price }</div></td>
						<td height="20" bgcolor="#FFFFFF">
							<div align="center" class="STYLE2 STYLE1">
							<!-- 下面这是jstl的语法，用来判断类型是什么的,并根据相应的类型显示出来 -->
							<c:choose>
								<c:when test="${product.type == '1'}">
									半身头像
								</c:when>
								<c:when test="${product.type == '2'}">
									情侣合照
								</c:when>
								<c:otherwise>
									暂未分组
								</c:otherwise>
							</c:choose>
						</div></td>
						<td height="20" bgcolor="#FFFFFF">
							<div align="center">
								<%-- <s:if test="!isShow"><img src="images/001.gif" width="9" height="9" /><span class="STYLE1">[</span><a href="productShowA.action?id=<s:property value="id"/>&status=${status}&page=${page}">设为推荐</a><span class="STYLE1">]</span></s:if>
								<s:if test="isShow"><img src="images/010.gif" width="9" height="9" /><span class="STYLE1">[</span><a href="productShowDelete.action?id=<s:property value="id"/>&status=${status}&page=${page}">取消推荐</a><span class="STYLE1">]</span></s:if>
								<s:if test="!isSale"><img src="images/001.gif" width="9" height="9" /><span class="STYLE1">[</span><a href="productSaleA.action?id=<s:property value="id"/>&status=${status}&page=${page}">设为促销</a><span class="STYLE1">]</span></s:if>
								<s:if test="isSale"><img src="images/010.gif" width="9" height="9" /><span class="STYLE1">[</span><a href="productSaleDelete.action?id=<s:property value="id"/>&status=${status}&page=${page}">取消促销</a><span class="STYLE1">]</span></s:if>
								<s:if test="!isNew"><img src="images/001.gif" width="9" height="9" /><span class="STYLE1">[</span><a href="productNewA.action?id=<s:property value="id"/>&status=${status}&page=${page}">设为最新</a><span class="STYLE1">]</span></s:if>
								<s:if test="isNew"><img src="images/010.gif" width="9" height="9" /><span class="STYLE1">[</span><a href="productNewDelete.action?id=<s:property value="id"/>&status=${status}&page=${page}">取消最新</a><span class="STYLE1">]</span></s:if>
								<br> --%>
								<%-- <img src="images/037.gif" width="9" height="9" /><span class="STYLE1">[</span><a href="${pageContext.request.contextPath}/admin/upProduct?pid=${product.pid }">修改</a><span class="STYLE1">]</span> --%>
								<img src="images/083.gif" width="9" height="9" /><span class="STYLE1">[</span><a onclick="return action()" href="${pageContext.request.contextPath}/admin/delProduct?pid=${product.pid }">删除</a><span class="STYLE1">]</span>
							</div>
						</td>
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
			<td background="images/tab_21.gif">${pageTool}</td>
			<td width="14"><img src="images/tab_22.gif" width="14" height="29" /></td>
		  </tr>
		</table>
	</td>
  </tr>
</table>
</body>
<script type="text/javascript">
function action(){
	var s=confirm("是否确定删除？"); 
	if(s==false){
		return false;
	}
}
</script>
</html>
