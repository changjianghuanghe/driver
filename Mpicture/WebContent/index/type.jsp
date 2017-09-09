<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!-- 自适应设置 -->
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
    <!-- 引入外部css样式 -->
    <link rel="stylesheet" href="../css/stylees.css" />
    <!-- 自适应样式 -->
	<link rel="stylesheet" href="../css/xitongyangshi.css" />
    <title></title>
  <style type="text/css">
  	img{
  		max-height: 100%;
  	}
  </style>
</head>
<script type="text/javascript" src="../js/jquery-3.1.1.js"></script>
<!-- 自适应判断 -->
<script type="text/javascript" src="../js/Adaptive.js"></script>
<!-- 获取数据 -->
<script type="text/javascript">
$(function(){
	 setTimeout(indexboder(), 0); 
	 /*function productHeadList(type){
		$.ajax({
			url:"../index/productHeadList?nowpage=1&type="+type,
			type: 'POST',
	       	dataType: 'text',	//返回数据格式
	       	async:true,
	       	contentType: "application/json; charset=utf-8",
	       	success:function(data){
	       		var json=JSON.parse(data);
	       		var pic=json.product;
	       		var page=json.page;
	       		var img=$(".to");
	       		var font=$(".headstyle");
	       		var hrefs=$(".hrefs");
	       		for(var i=0;i<pic.length;i++){
	       			img[i].src=pic[i].cover;
	       			hrefs[i].href="toindent.html?pid="+pic[i].pid;
	       			font.eq(i).html(pic[i].name);
	       		}
	       	},	
	       	error:function(){
	       		console.log("数据获取失败");
	       	}
		}); 
	} */
	
	function indexboder(){
		var type="${type}";
		if(type==1){
			$("#indexboder").css("marginLeft","8%");
		}else{
			$("#indexboder").css("marginLeft","57%");
		}
	}
	$("#right").click(function(){
		location.href="menu.html";
	});
	$("#leftArrow").click(function(){
		location.href="";
	});
	
	$(".fonttoleft").click(function(){
		//$("#indexboder").css("marginLeft","8%");
		location.href="../index/productHeadList?nowpage=1&type=1";
	});
	$(".fonttoright").click(function(){
		//$("#indexboder").css("marginLeft","57%");
		location.href="../index/productHeadList?nowpage=1&type=2";
	}); 
});
</script>
<body>
<div style="width: 100%;height: 100%;">
	
	<!-- 页面内容 -->
	<div style="width: 100%;height: 2em;margin-top: 5em;">
	   <center>
	        <table>
	            <tr>
	                <!-- padding：顺序是上，右，下，左    顺时针进行的。 -->
	                <td style="padding: 15px 25px 0px 0px;color: #333333;"><p class="fonttoleft">半身头像&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p></td>
	                <td style="padding: 15px 0px 0px 50px;"><p class="fonttoright">情侣合照</p></td>
	            </tr>
	        </table>
	    </center>
	    <div id="indexboder"></div>
	</div>
	<input type="text" id="type" value="${type }" hidden="hidden"/>
	<!-- 页面内容 -->
	<div style="width: auto;height: 30.9em; position: relative; top: 2em;padding-bottom: 3em;">
		<div class="totop">
			<c:forEach items="${products }" var="product" varStatus="status">
				<a class="hrefs" href="toindent.html?pid=${product.pid }">
				    <div class="toleft">
				        <img class="to" src="${product.cover }">
				        <p class="headstyle">${product.name }</p>
				        <img src="img/buttonstyle.jpg" class="image">
				    </div>
			    </a>
			</c:forEach>
			
		    
		</div>
	</div>
	
	
	<!-- 页头导航栏 -->
	<div class="todown">
		<div style="height: 3.5em;width: 100%;position: fixed;top: 0;background:#FFAC66;">
			<!-- 页眉内容 -->
	        <div class="down">
	        	<div class="stylees-forward"><img src="img/kongbai.png" style=""/></div>
	        	<div class="stylees-wanzi">选择类型</div>
			    <br />
			    <!-- 水平分割线 -->
			    <!-- <div id="level"></div> -->
		   </div>
	   </div>
	</div>
	
	
	
        
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
</div>
</body>
</html>