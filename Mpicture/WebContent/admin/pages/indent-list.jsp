<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
			<span class="STYLE4">订单列表</span></td>
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
					<td width="5%" height="36" background="images/tab_14.gif" class="STYLE1">
						<div align="center" class="STYLE2 STYLE1">编号</div></td>
					<td width="20%" height="36" background="images/tab_14.gif" class="STYLE1">
						<div align="center" class="STYLE2 STYLE1">下单时间</div></td>
					<td width="10%" height="36" background="images/tab_14.gif" class="STYLE1">
						<div align="center" class="STYLE2 STYLE1">用户照片</div></td>
					<td width="10%" height="36" background="images/tab_14.gif" class="STYLE1">
						<div align="center" class="STYLE2 STYLE1">下单用户</div></td>
					<td width="10%" height="36" background="images/tab_14.gif" class="STYLE1">
						<div align="center" class="STYLE2 STYLE1">用户邮箱</div></td>
					<td width="10%" height="36" background="images/tab_14.gif" class="STYLE1">
						<div align="center" class="STYLE2 STYLE1">订单状态</div></td>
					<td width="10%" height="36" background="images/tab_14.gif" class="STYLE1">
						<div align="center" class="STYLE2 STYLE1">订单类型</div></td>
					<td width="25%" height="36" background="images/tab_14.gif" class="STYLE1">
						<div align="center" class="STYLE2">操作</div></td>
				</tr>
				<c:forEach items="${indentList}" var="indent" varStatus="status">
					<tr>
						<td height="30" bgcolor="#FFFFFF">
							<div align="center" class="STYLE2 STYLE1">${indent.tid }</div></td>
						<td height="20" bgcolor="#FFFFFF">
							<div align="center" class="STYLE2 STYLE1"><f:formatDate value="${indent.systime}" pattern="yyyy-MM-dd HH:MM:ss"></f:formatDate></div></td>
						<td height="20" bgcolor="#FFFFFF">
							<div align="center" class="STYLE2 STYLE1"><img onclick="getbighead(this)" style="width: 38%;height: 38%;border-radius: 50%;" src="${indent.userphoto }"> </div></td>
						<td height="20" bgcolor="#FFFFFF">
							<div align="center" class="STYLE2 STYLE1">${indent.users_id.username }</div></td>
						<td height="20" bgcolor="#FFFFFF">
							<div align="center" class="STYLE2 STYLE1">${indent.e_mail }</div></td>
						<td height="20" bgcolor="#FFFFFF">
							<div align="center" class="STYLE2 STYLE1">${indent.statusintr }</div></td>
						<td height="20" bgcolor="#FFFFFF">
							<div align="center" class="STYLE2 STYLE1">${indent.product_id.name }</div></td>	
						<td height="20" bgcolor="#FFFFFF">
							<div align="center">
								<!-- <img src="images/a1.gif" width="9" height="9" /><span class="STYLE1">[</span><a href="itemList.action?id=">查看详情</a><span class="STYLE1">]</span> -->
								<%-- <s:if test="status==1">
									<img src="images/037.gif" width="9" height="9" /><span class="STYLE1">[</span><a href="indentDispose.action?id=<s:property value="id"/>&status=${status}&page=${page}">发货</a><span class="STYLE1">]</span>
								</s:if> --%>
								  <select onchange="selectStatus(${indent.tid},this.value)" style="float: left;margin-left: 10%;margin-top: 1%">
								 	<option value="-1">修改状态</option>
								 	<option value="0">退回单</option>
								    <option value="1">未接单</option>
								    <option value="2">已接单</option>
								    <option value="3">待确认</option>
								    <option value="4">已结单</option>
								    <option value="5">修改中</option>
								    <option value="6">未生效</option>
								  </select>
								<span class="STYLE1">[</span><a onclick="return updateStatus()" href="">确定</a><span class="STYLE1">]</span>
								<img src="images/083.gif" width="9" height="9" /><span class="STYLE1">[</span><a onclick="return action()" href="${pageContext.request.contextPath}/admin/indentDelete?tid=${indent.tid}">删除</a><span class="STYLE1">]</span>
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
			<td background="images/tab_21.gif"></td>
			<td width="14"><img src="images/tab_22.gif" width="14" height="29" /></td>
		  </tr>
		</table>
	</td>
  </tr>
</table>
<div class="pagelist">
	 <select onchange="status(this)" style="float: left;margin-left: 0.5%;height: 20px;width: 100px;">
	 	<option value="-1">订单类型</option>
	 	<option value="0">退回单</option>
	    <option value="1">未接单</option>
	    <option value="2">已接单</option>
	    <option value="3">待确认</option>
	    <option value="4">已结单</option>
	    <option value="5">修改中</option>
	    <option value="6">未生效</option>
	  </select>
	
	<div align="center" class="STYLE2 STYLE1"><a onclick="return pageshang(${pageClass.nowpage})" 
	href="${pageContext.request.contextPath}/admin/indentList?nowpage=${pageClass.nowpage-1}&status=${status}"  style="margin-left: 48%;">上一页</a></div>	
	
	<p style="font-size: 12px;margin-left: 85%;margin-top: -2.5%;">当前页 :${pageClass.nowpage }</p>	
	
	<div align="center" class="STYLE2 STYLE1"><a onclick="return pagexia(${pageClass.nowpage},${pageClass.pagetotal})"  
	href="${pageContext.request.contextPath}/admin/indentList?nowpage=${pageClass.nowpage+1}&status=${status}" style="margin-left:90%;margin-top: -2%">下一页</a></div>

	
</div>
		
</body>
<script type="text/javascript">
function status(select){
	var val=select.value;
	if(val!=-1){
		location.href="../admin/indentList?nowpage=1&status="+val;
	}
}
function pageshang(nowpage){
	if(nowpage==1){
		alert("当前已经是第一页！");
		return false;
	}
}
function pagexia(nowpage,pagetotal){
	if(nowpage==pagetotal){
		alert("当前已经是最后一页！");
		return false;
	}
}
function action(){
	var s=confirm("是否确定删除？"); 
	if(s==false){
		return false;
	}
}
var selecttid;
var selectval;
function selectStatus(tid,val){
	selecttid=tid;
	selectval=val;
}
function updateStatus(){
	location.href="${pageContext.request.contextPath}/admin/updateStatus?tid="+selecttid+"&status="+selectval;
	return false;
}
function getbighead(img){
	window.open("${pageContext.request.contextPath}/admin/pages/seeBigHead.jsp?imgsrc="+img.src);
}
</script>
</html>
