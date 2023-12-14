<%--设置 JSP 页面生成的响应的内容类型和页面编码--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<!-- top部件 -->
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>e诚尚品</title>
	<%@include file="../comms/source.jsp"%>
	<!--script-->
</head>
<body>
<!--top-header-->
<div class="header">
	<%@include file="../comms/top.jsp"%>
	<%@include file="../comms/header.jsp"%>
</div>
<!--/top-header-->

<!-- /top部件 -->
<div class="container">
	<div class="col-sm-1"></div>
	<div class="col-sm-10">
		<div class="cart">
			<form class="form-horizontal" method="post" action="">
				<div class="col-sm-12">
					<h3>
						<i class="fa fa-shopping-cart"></i> 我的订单
					</h3>
					<table class="table">
						<tr>
							<th>#</th>
							<th>订单号</th>
							<th>下单日期</th>
							<th>总金额</th>
							<th>订单状态</th>
							<th></th>
						</tr>
						<c:forEach items="${orderList}" var="order" varStatus="status">
							<tr>
								<td>${status.count}</td>
								<td>${order.ocode}</td>
								<td><fmt:formatDate value="${order.otime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
								<td>¥ ${order.ototal}</td>
								<td>
									<c:choose>
										<c:when test="${order.ostatus == 1}">
											已下单
										</c:when>
										<c:when test="${order.ostatus == 2}">
											已发货
										</c:when>
										<c:otherwise>
											已收货
										</c:otherwise>
									</c:choose>
								</td>
								<td>
									<c:if test="${order.ostatus == 2}">
										<a href="${ctx}/account/order/confirm.html?oid=${order.oid}" onclick="return confirm('是否确认收货？')">确认收货</a>
									</c:if>
								</td>
							</tr>
						</c:forEach>

						
						<tr><th colspan="6"><span style="height: 40px">&nbsp;</span></th></tr>
					</table>
				</div>
			</form>
		</div>
	</div>
</div>
<!---->
<%@include file="../comms/footer.jsp"%>
</body>
</html>