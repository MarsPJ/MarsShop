<%--设置 JSP 页面生成的响应的内容类型和页面编码--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- top部件 -->

<!DOCTYPE html>
<html>
<head>
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
<!-- start content -->
<div class="container">

	<div class="women-product">
		<div class=" w_content">
			<div class="women">
				<a href="#"><h4>查询结果</h4></a>
				<div class="clearfix"></div>
			</div>
		</div>
		<!-- grids_of_4 -->
		<div class="grid-product">
			<c:forEach items="${goodsList}" var="g" varStatus="status">
				<div class="  product-grid">
					<div class="content_box">
						<a href="${ctx}/detail.html?gdId=${g.gdId}">
							<div class="left-grid-view grid-view-left">
								<img src="${ctx}/upload/${g.gdImage}"
									 class="img-responsive watch-right" alt="" />
								<div class="mask">
									<div class="info">Quick View</div>
								</div>
							</div>
						</a>
					</div>
					<h4>
						<a href="#">${g.type.tname}</a>
					</h4>
					<p>${g.gdName}</p>
					¥ ${g.gdPrice}
				</div>
			</c:forEach>
			<div class="clearfix"></div>
			<nav aria-label="Page navigation">
				<ul class="pagination">
					<li class="${page.pageIndex == 1 ? 'disabled' : ''}">
						<a href="${ctx}/list.html?pageIndex=${page.prevPageIndex}&tid=${param.tid}&gdName=${param.gdName}" aria-label="Previous">
							<span aria-hidden="true">&laquo;</span>
						</a>
					</li>
					<c:forEach begin="1" end="${page.totalPage}" step="1" varStatus="status">
						<li class="${status.count == page.pageIndex ? 'active' : ''}">
							<a href="${ctx}/list.html?pageIndex=${status.count}&tid=${param.tid}&gdName=${param.gdName}">${status.count}</a></li>
					</c:forEach>
					<li class="${page.pageIndex == page.totalPage ? 'disabled' : ''}">
						<a href="${ctx}/list.html?pageIndex=${page.nextPageIndex}&tid=${param.tid}&gdName=${param.gdName}" aria-label="Next">
							<span aria-hidden="true">&raquo;</span>
						</a>
					</li>
				</ul>
			</nav>
		</div>
	</div>
	<!-- left.jsp -->
	<%@include file="comms/left.jsp"%>
	<!-- /left.jsp -->
	<div class="clearfix"></div>
</div>
<!---->
	<%@include file="comms/footer.jsp"%>
</body>
</html>