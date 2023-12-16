<%--设置 JSP 页面生成的响应的内容类型和页面编码--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<!-- top部件 -->
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
<title>MarsShop</title>
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

	<div class="container">
		<div class="banner-menu">
			<div class="shoes-grid">
				<div class="wmuSlider example1 slide-grid">
					<div class="wmuSliderWrapper">
						<article style="position: absolute; width: 100%; opacity: 0;">
							<div id="banner1">
								
							</div>
						</article>
						<article style="position: absolute; width: 100%; opacity: 0;">
							<div id="banner2">
								
							</div>
						</article>
						<article style="position: absolute; width: 100%; opacity: 0;">
							<div id="banner3">
								
							</div>
						</article>
					</div>
					<ul class="wmuSliderPagination">
						<li><a href="#" class="">0</a></li>
						<li><a href="#" class="">1</a></li>
						<li><a href="#" class="">2</a></li>
					</ul>
				</div>

				<div class="products">
					<h5 class="latest-product">最新上架</h5>
				</div>
				<div class="product-left">
				
				<c:forEach items="${goodsList}" var="g" varStatus="status">
					<div class=" chain-grid ${status.count % 3 == 0 ? 'grid-top-chain' : ''}"
						 style="backgroud-image: url(${ctx}/upload/${g.gdImage})">
						<a href="${ctx}/detail.html?gdId=${g.gdId}"><img class="img-responsive chain" style="height:250px"
												   src="${ctx}/upload/${g.gdImage}" alt=" " /></a> <span
							class="star"> </span>
						<div class="grid-chain-bottom">
							<h6>${g.gdName}</h6>
							<div class="star-price">
								<div class="dolor-grid">
									<span class="actual">¥ ${g.gdPrice}</span>
								</div>
								<a class="now-get get-cart" href="javascript:void(0)" onclick="addCar('${g.gdId}')">加入购物车</a>
								<div class="clearfix"></div>
							</div>
						</div>
					</div>
				</c:forEach>

						

					
					<div class="clearfix"></div>
				</div>
				<div class="clearfix"></div>
			</div>
			<!-- left.jsp -->
			<%@include file="comms/left.jsp"%>
			<!-- /left.jsp -->
			<div class="clearfix"></div>
		</div>
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