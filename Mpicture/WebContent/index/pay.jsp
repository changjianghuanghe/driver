<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
<link rel="stylesheet" href="../css/stylees.css" />
<!-- 自适应样式 -->
<link rel="stylesheet" href="../css/xitongyangshi.css" />
<title></title>
</head>
<script type="text/javascript" src="../js/jquery-3.1.1.js"></script>
<!-- 自适应判断 -->
<script type="text/javascript" src="../js/Adaptive.js"></script>
<script type="text/javascript" src="../js/wx.sdk.js"></script>
<script type="text/javascript">
	$(function(){
		wx.config({
		      debug: false,
		      appId: $("#appId").val(),
		      timestamp: $("#timeStamp").val(),
		      nonceStr: $("#nonceStr").val(),
		      signature: $("#paySign").val(),
		      jsApiList: [       
		        'chooseWXPay'
		      ]
		  });
		  wx.ready(function () {
            wx.chooseWXPay({
	              timestamp: $("#timeStamp").val(),
	              nonceStr: $("#nonceStr").val(),
	              package: $("#packageValue").val(),
	              signType: "MD5",
	              paySign: $("#paySign").val(),
	              success: function (res) {
	            	  $("#status").html("充值成功,跳转中...");
	            	  setCapital(1);
	              },
	              cancel: function(res) {
	            	  $("#status").html("充值取消,跳转中...");
	            	  location.href="mybalance.html";   //用户点击取消时的回调函数，仅部分有用户取消操作的api才会用到。
	              },
	              fail: function(res) {
	            	  $("#status").html("充值失败,跳转中...");
	            	  location.href="mybalance.html";     //接口调用失败时执行的回调函数。
	              }
              });
           });
		  wx.error(function(res){
			  $("#status").html("充值发生错误,跳转中...");
			  location.href="mybalance.html";
		  }); 
	});
	function setCapital(status){
		$(function(){
			$.ajax({
				url:"../index/returnStatus?status="+status,
				type: 'POST',
		       	dataType: 'text',	//返回数据格式
		       	async:true,
		       	contentType: "application/json; charset=utf-8",
		       	success:function(data){
		       		if(data!=null){
		       			location.href="mybalance.html";
		       		}
		       	},	
		       	error:function(){
		       		console.log("数据获取失败");
		       	}
			});
		});
	}
</script>
<body>
	<h2 id="status"></h2><br/>
	<input type="text" id="openid" value="${openid}" hidden="hidden"/><br/>
	<input type="text" id="orderNo" value="${orderNo}" hidden="hidden"/><br/>
	<input type="text" id="appId" value="${appId}" hidden="hidden"/><br/>
	<input type="text" id="timeStamp" value="${timeStamp}" hidden="hidden"/><br/>
	<input type="text" id="nonceStr" value="${nonceStr}" hidden="hidden"/><br/>
	<input type="text" id="paySign" value="${paySign}" hidden="hidden"/><br/>
	<input type="text" id="packageValue" value="${packageValue}" hidden="hidden"/>
</body>
</html>