<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
<link rel="stylesheet" href="../css/stylees.css" />
<!-- 自适应样式 -->
<link rel="stylesheet" href="../css/xitongyangshi.css" />
<title></title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.1.1.js"></script>
<script type="text/javascript"
	src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<!-- 自适应判断 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/Adaptive.js"></script>
<script type="text/javascript">
	$(function() {
		$(".transparent").click(
				function() {
					var url = encodeURI(location.href.split('#')[0]);
					$.ajax({
						url : "../index/fileUpload?url=" + url,
						type : 'POST',
						dataType : 'text', //返回数据格式
						async : true,
						contentType : "application/json; charset=utf-8",
						success : function(data) {
							var json = JSON.parse(data);
							wx.config({
								debug : false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
								appId : 'wx00fd10c78cc22c51', // 必填，公众号的唯一标识
								timestamp : json.timestamp, // 必填，生成签名的时间戳
								nonceStr : json.nonceStr, // 必填，生成签名的随机串
								signature : json.signature,// 必填，签名，见附录1
								jsApiList : [ 'chooseImage', 'uploadImage']
							// 必填，需要使用的JS接口列表，所有JS接口列表见附录2
							});
							wx.ready(function() {
								//拍照或从手机相册中选图接口
								wx.chooseImage({
									count : 1, // 最多能选择多少张图片，默认9
									sizeType : [ 'original', 'compressed' ], // 可以指定是原图还是压缩图，默认二者都有
									sourceType : [ 'album', 'camera' ], // 可以指定来源是相册还是相机，默认二者都有
									success : function(res) {
										var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
										//上传图片接口
										wx.uploadImage({
											localId : localIds.toString(), // 需要上传的图片的本地ID，由chooseImage接口获得
											isShowProgressTips : 1, // 默认为1，显示进度提示
											success : function(res) {
												var serverId = res.serverId; // 返回图片的服务器端ID
												$.ajax({
													url:"../index/downloadhead?serverId="+serverId,
													type: 'POST',
											       	dataType: 'text',	
											       	async:true,
											       	contentType: "application/json; charset=utf-8",
											       	success:function(data){
											       		var json=JSON.parse(data);
											       		$(".bagimg").attr('src', json.filepath);
											       		$("#userphoto").val(json.filepath);
											       	}
												});
											}
										});
										
									}
								});
							});
						}
					});
				});

		$("#updateindent").click(function() {
			var userphoto=$("#userphoto").val();
			var require_text=$("#require_text").val();
			var email=$("#email").val();
			if(userphoto==""||require_text==""||email==""){
				alert("输入内容不能为空，或头像未上传!");
			}else{
				$("#indentform").submit();
			}
		});
	});
</script>
</head>
<style>
html, body {
	height: 100%;
}

.bagimg {
	height: 28%;
	width: 50%;
	margin-left: 24%;
	margin-top: 18%;
	border-radius: 3%;
}

.transparent {
	height: 4%;
	width: 50%;
	border-bottom-left-radius: 7%;
	border-bottom-right-radius: 7%;
	margin-left: 24%;
	top: -8%;
	background-color: #333333;
	filter: alpha(Opacity = 80);
	-moz-opacity: 0.5;
	opacity: 0.5;
}

.action {
	text-align: center;
	font-size: 0.8em;
	line-height: 0.7em;
	color: #999999;
}

.textarea1 {
	width: 84%;
	height: 5em;
	margin-left: 8%;
	margin-top: 5%;
	overflow: hidden;
	resize: none;
	border-radius: 2%;
}
</style>
<body>
<div style="height: 100%;width: 100%;position: fixed;">
	
	<!-- 导航栏  -->
	<div class="todown">
		<div style="height: 3.5em;width: 100%;position: fixed;top: 0;background:#FFAC66;">
			<!-- 页眉内容 -->
	        <div class="down">
	        	<div class="stylees-right"><a href="myindent.html"><img src="img/right.png" style="width: 3%; height: 3%"/></a></div>
	        	<div class="stylees-wanzi">自拍或上传</div>
			    <br />
		   </div>
	   </div>
	</div>

	<!-- <br/>
	<div id="level"></div> -->
	
	<!-- 预览图片 -->
	<div class="bagimg"><img src="img/head.jpg"></img></div>
	
	<div class="transparent">
		<p style="color: white; text-align: center">点击图片上传</p>
	</div>
	<p class="action">请上传大小不超过10M的JPG文件</p>
	<p class="action">（胸部以上半身照）</p>

	<!-- 提交用户订单要求 -->
	<form id="indentform" action="${pageContext.request.contextPath}/index/updateindent"
		method="post" enctype="multipart/form-data">
		<input id="tid" name="tid" value="${tid}" hidden="hidden" /> 
		<input id="userphoto" name="userphoto" value="" hidden="hidden" />
		<textarea id="require_text" name="require_text" placeholder="请输入你的要求说明（不超过80字)"
			class="textarea1"></textarea>
		<input id="email" name="e_mail" placeholder="请输入你的E-mail   方便我们给您发送文件" 
		style="height: 2.5em; width: 84%; margin-left: 8%; margin-top: 2%;">
	</form>
	<!-- 确认上传图片 -->
	<img id="updateindent"
		style="width: 40%; height: 3em;margin-left: 30%;margin-top:3%; bottom: 10em"
		src="img/upload.png">


	
	<!-- 页脚内容 -->
	<div class="footdiv">
		<a href="../index/productHeadList?nowpage=1&type=1">
			<div style="position: fixed;width: 50%;left: 0;">
				<img src="img/dingzhiicon.png" style="width: 15%;height: 15%;margin-left: 5em;">
				<div style="margin-left: 6em;font-size:0.8em;color:gray;">定制</div>
			</div>
		</a>
		<a href="menu.html">
			<div style="position: fixed;width: 50%;right: 0;">
				<img src="img/myicon.png" style="width: 15%;height: 15%;margin-left: 4.8em;">
				<div style="margin-left: 6em;font-size:0.8em;color:gray;">我的</div>
			</div>
		</a>
	</div>

</body>
</html>