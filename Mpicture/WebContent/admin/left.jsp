<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
html, body{
	width: 100%;
	height: 100%;
	margin: 0px 0px;
	overflow: hidden;
}
dl,ul,li{list-style:none;}
a{color:#000;}
a:link,a:visited,a:active,a:hover{text-decoration:none;}
.meun_div{width:110px;height:auto;border-top:0;background:#fff;padding-top:10px;font-size:14px;}
.meun_top{width:80px;height:27px;line-height:27px;padding-left:20px;}
.meun_img{float:left;width:7px;height:27px;background:url(images/menu_1.gif) no-repeat;}
.meun_box ul{margin:0;padding:0 5px 0 19px;font-size:12px;}
.meun_box li{width:auto;height:20px;line-height:20px;margin:3px 0;padding-left:2px;}
.none_box {display:block;}
</style>
<script type="text/JavaScript"> 
var $=function(id) {
   return document.getElementById(id);
};

//显示子菜单
function show_menu_box(num){
	if($('box'+num)){   
		if($('box'+num).style.display=='none'){
			$('box'+num).style.display='block';
			$('img'+num).style.background='url(images/menu_2.gif)';
		}else {
			$('box'+num).style.display='none';
			$('img'+num).style.background='url(images/menu_1.gif)';
		}
	}
}

</script>
</head>

<body>
<table width="173" height="100%" border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
  <tr>
    <td width="169" valign="top">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		  <tr>
			<td height="20" background="images/main_11.gif">&nbsp;</td>
		  </tr>
		  <tr>
			<td>
				<div class="meun_div" align="center">
				
					<div class="meun_top">
						<div id="img1" class="meun_img"></div>
						<a onclick="show_menu_box(1)" href="javascript:;">订单管理</a>
					</div>
					<div class="meun_box none_box" id="box1">
						<ul>
							<li><a href="${pageContext.request.contextPath}/admin/indentList?nowpage=1"  target="rightFrame">&middot;订单列表</a></li>
							<!-- <li><a href="indentList.action?status=1"  target="rightFrame">&middot;订单列表</a></li> -->
							<!-- <li><a href="indentList.action?status=2"  target="rightFrame">&middot;已处理</a></li> -->
						</ul>
					</div>
					
					<!-- <div class="meun_top">
						<div id="img2" class="meun_img"></div>
						<a onclick="show_menu_box(2)" href="javascript:;">顾客管理</a>
					</div>
					<div class="meun_box none_box" id="box2">
						<ul>
							<li><a href="userList"  target="rightFrame">&middot;顾客列表</a></li>
							<li><a href="pages/user-add.jsp"  target="rightFrame">&middot;添加顾客</a></li>
						</ul>
					</div> -->
					
					<div class="meun_top">
						<div id="img3" class="meun_img"></div>
						<a onclick="show_menu_box(3)" href="javascript:;">商品管理</a>
					</div>
					<div class="meun_box none_box" id="box3">
						<ul>
							<li><a href="${pageContext.request.contextPath}/admin/commodityList"  target="rightFrame">&middot;商品列表</a></li>
							<li><a href="${pageContext.request.contextPath}/admin/addProduct"  target="rightFrame">&middot;添加商品</a></li>
							<!-- <li><a href="productList.action?status=1"  target="rightFrame">&middot;推荐列表</a></li>
							<li><a href="productList.action?status=2"  target="rightFrame">&middot;促销列表</a></li>
							<li><a href="productList.action?status=3"  target="rightFrame">&middot;最新列表</a></li> -->
						</ul>
					</div>
					
					<!-- <div class="meun_top">
						<div id="img4" class="meun_img"></div>
						<a onclick="show_menu_box(4)" href="javascript:;">库存管理</a>
					</div>
					<div class="meun_box none_box" id="box4">
						<ul>
							<li><a href="stockList.action"  target="rightFrame">&middot;库存列表</a></li>
							<li><a href="stockWarn.action"  target="rightFrame">&middot;库存预警</a></li>
						</ul>
					</div> -->
					
					<!-- <div class="meun_top">
						<div id="img5" class="meun_img"></div>
						<a onclick="show_menu_box(5)" href="javascript:;">类目管理</a>
					</div>
					<div class="meun_box none_box" id="box5">
						<ul>
							<li><a href="categoryList.action"  target="rightFrame">&middot;类目列表</a></li>
							<li><a href="pages/category-add.jsp"  target="rightFrame">&middot;添加类目</a></li>
						</ul>
					</div> -->
					
					<div class="meun_top">
						<div id="img6" class="meun_img"></div>
						<a onclick="show_menu_box(6)" href="javascript:;">系统管理</a>
					</div>
					<div class="meun_box none_box" id="box6">
						<ul>
							<li><a href="${pageContext.request.contextPath}/admin/adminList?nowpage=1"  target="rightFrame">&middot;用户列表</a></li>
							<li><a href="${pageContext.request.contextPath}/admin/addAdmin"  target="rightFrame">&middot;添加用户</a></li>
							<li><a href="${pageContext.request.contextPath}/admin/listpicture"  target="rightFrame">&middot;最近完成</a></li>
						</ul>
					</div>
					
					<div class="meun_top">
						<div id="img7" class="meun_img"></div>
						<a onclick="show_menu_box(7)" href="javascript:;">客户管理</a>
					</div>
					<div class="meun_box none_box" id="box7">
						<ul>
							<li><a href="${pageContext.request.contextPath}/admin/userList?nowpage=1"  target="rightFrame">&middot;客户列表</a></li>
							<%-- <li><a href="${pageContext.request.contextPath}/admin/addAdmin"  target="rightFrame">&middot;添加用户</a></li> --%>
						</ul>
					</div>
					
				</div>
			</td>
		  </tr>
		</table>
	</td>
  </tr>
</table>
</body>
</html>