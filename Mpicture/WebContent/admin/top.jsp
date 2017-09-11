<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.1.1.js"></script>
<script type="text/javascript">
$(function(){
	window.setInterval(pollingAjax, 5000); 
	function pollingAjax(){
		$.ajax({
			url:"../admin/pollingAjax",
			type: 'POST',
	       	dataType: 'text',	//返回数据格式
	       	async:true,
	       	contentType: "application/json; charset=utf-8",
	       	success:function(data){
	       		var json=JSON.parse(data);
	       		if(json.status==true){
	       			$("#message").html("有客户未接单<br>请查看订单列表");
	       		}else{
	       			$("#message").html("");
	       		}
	       	},	
	       	error:function(){
	       		console.log("轮询请求失败");
	       	}
		});
	}
});
</script>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE1 {
	font-size: 12px;
	color: #FFFFFF;
}
.STYLE2 {
	font-size: 12px;
	color: #333333;
}
-->
a:link {font-size:12px; color:#000000; text-decoration:none;}
a:visited {font-size:12px; color:#000000; text-decoration:none;}
a:hover {font-size:12px; color:#00CCFF;text-decoration:none;}
.STYLE4 {font-size: 12px}
</style></head>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="36" background="images/main_07.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
             <td width="282" height="52" background="images/main_05.gif">
        	<span style="font-size:24px;font-weight:bolder;margin-left:60px;">
        		萌小你后台管理</span>
        </td>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td>
	            <span class="STYLE1">
	            	<img src="images/home.gif" width="12" height="13"> 
	            </span>
	            <span class="STYLE4">
	            	<a href="javascript:location.reload()"  target="_blank">网站首页</a>
	            </span>
	            <span class="STYLE1"> </span>
            </td>
          </tr>
        </table></td>
        <td width="247" background="images/main_08.gif">&nbsp;</td>
        <td width="283" background="images/main_09.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
          	<td><p style="font-size: 0.5em;color: red;" id="message"></p></td>
            <td>
            	<img src="images/uesr.gif" width="14" height="14">
            	<span class="STYLE2"> 当前登录用户:${sessionScope.admin.username }</span>
            	<span class="STYLE1"> 
	            	<img src="images/quit.gif" width="16" height="13"> 
	            </span>
	            <span class="STYLE4">
	            	<a href="${pageContext.request.contextPath}/admin/loginout" target=_top>退出</a>
	            </span>
            </td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
