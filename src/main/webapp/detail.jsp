<%--设置 JSP 页面生成的响应的内容类型和页面编码--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- top部件 -->



<!DOCTYPE html>
<html>
<head>
<title>e诚尚品</title>
<%@include file="comms/source.jsp"%>
<!--script-->
</head>
<body>
	<!--top-header-->
	<div class="header">
		<%@include file="comms/top.jsp"%>
		<%@include file="comms/header.jsp"%>
	</div>
	<!--/top-header-->

<!-- /top部件 -->

<!-- start content -->
<div class="container">

	<div class=" single_top">
		<div class="single_grid">
			<div class="grid images_3_of_2">
				<ul id="etalage">
					<li><a href="optionallink.html"> <img
							class="etalage_thumb_image" src="${ctx}/upload/${goods.gdImage}"
							style="width: 300px" class="img-responsive" /> <img
							class="etalage_source_image" src="images/si4.jpg"
							class="img-responsive" title="" />
					</a></li>
				</ul>
				<div class="clearfix"></div>
			</div>
			<div class="desc1 span_3_of_2">
				<ul class="back">
					<li><i class="back_arrow"> </i>Back to <a href="${ctx}">首页</a></li>
				</ul>
				<h1>${goods.gdName}</h1>
				<ul class="price_single">
					<li class="head"><h2>¥ ${goods.gdPrice}</h2></li>
					<li class="clearfix"></li>
				</ul>
				<p>
					<span>库存：${goods.gdQuantity}</span><span>发货地：${goods.gdCity}</span>
				</p>

				<a href="javascript:void(0)" onclick="addCar('${goods.gdId}')" class="now-get">加入购物车</a>

			</div>
			<div class="clearfix"></div>
		</div>

		<div class="toogle">
			<h3 class="m_3">商品简介</h3>
			<pre class="m_text" style="white-space: break-spaces;">${goods.gdInfo}</pre>
		</div>
	</div>

	<!---->
	<!-- left.jsp -->
	<%@include file="comms/left.jsp"%>
	<!-- /left.jsp -->
	<div class="clearfix"></div>
</div>
<!---->
<%@include file="comms/footer.jsp"%>
<!---->


<%@include file="comms/modal.jsp"%>
	<script>
		function addCar(gdId) {
			if('${login_user}' == '') {
				modal.find('p').html('请先登录！');
				modal.modal('show');
				return;
			}
			// post方式提交，传递参数gdId，
			$.post('${ctx}/account/cart/add.html', {gdId}, function (res){
				// 设置回调函数
				// res可以接收controller发回的json消息
				if (res.code == 1) {
					modal.find('p').html('商品添加成功！');
				} else {
					modal.find('p').html('商品已经在购物车中！可前往购物车修改购买数量');
				}
				modal.modal('show');
			})
		}
	</script>
</body>
</html>