<%--设置 JSP 页面生成的响应的内容类型和页面编码--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--JSP页面中使用JSTL <c:...> 标签库的声明语句。它引入了 JSTL Core 库，其中包含了一组用于控制流程和基本数据操作的标签。--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 头部开始 -->
<%@include file="comms/header.jsp"%>
<!-- 头部结束 -->

<!-- 左边菜单开始 -->
<%@include file="comms/left-menu.jsp"%>
<!-- 左边菜单结束 -->

<div class="content">
	<div class="header">
		<h1 class="page-title">订单列表</h1>
		<ul class="breadcrumb">
			<li><a href="#">首页</a></li>
			<li><a href="#">订单管理</a></li>
			<li class="active">订单列表</li>
		</ul>
	</div>

	<div class="main-content">
		<div class="btn-toolbar list-toolbar">
			<div class="btn-group">
				<form action="${ctx}/admin/order" method="get" class="form-inline">
					<div class="form-group">
						<input class="form-control" style="width: 300px" placeholder="输入订单编号"
							   type="text" name="ocode" value="${param.ocode}">
						<input class="form-control" placeholder="输入会员姓名"
							   type="text" name="uname" value="${param.uname}">
						<input class="form-control" placeholder="选择下单开始时间"
							   type="date" name="otimeBegin" value="${param.otimeBegin}">-
						<input class="form-control" placeholder="选择下单结束时间"
							   type="date" name="otimeEnd" value="${param.otimeEnd}">
						<select class="form-control" name="ostatus">
							<option value="">所有订单状态</option>
							<option value="1">已下单</option>
							<option value="2">已发货</option>
							<option value="3">已收货</option>
						</select>
						<button class="btn btn-default" type="submit">
							<i class="fa fa-search"></i> 查询
						</button>
					</div>
				</form>
			</div>
		</div>

		<table class="table">
			<thead>
				<tr>
					<th>#</th>
					<th>订单号</th>
					<th>用户名</th>
					<th>订单金额</th>
					<th>下单时间</th>
					<th>订单状态</th>
					<th style="width: 2em;"></th>
					<th style="width: 2em;"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${orderList}" var="order" varStatus="status" >
					<tr>
						<td>${page.firstIndex + status.count}</td>
						<%--显示对话框同时更改iframe的src--%>
						<td><a href="javascript:void(0)" onclick="$('#alertModal').modal('show'),orderGoodsList.src='${ctx}/admin/order/detail?oid=${order.oid}'">${order.ocode}</a></td>
						<td>${order.user.uname}</td>
						<td>${order.ototal}</td>
						<td><fmt:formatDate value="${order.otime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
						<td>
							<c:choose>
								<c:when test="${order.ostatus == 1}">已下单</c:when>
								<c:when test="${order.ostatus == 2}">已发货</c:when>
								<c:otherwise>已收货</c:otherwise>
							</c:choose>
						</td>
						<td>
							<%--javascript:void(0)在事件处理程序中阻止默认的链接行为,即阻止跳转。然后默认调用onclick后面的内容--%>
							<%--通过 JavaScript 在运行时动态设置 <iframe> 的 src 属性，可以触发 <iframe> 重新加载新的内容--%>
							<a href="javascript:void(0)" role="button"  title="发货邮件提醒"
							   data-toggle="modal" onclick="sendEmail.src='${ctx}/admin/email/send?ocode=${order.ocode}&uid=${order.user.uid}&ostatus=${order.ostatus}'"><i class="fa fa-envelope" aria-hidden="true"></i></a>

						</td>
						<td>
							<c:if test="${order.ostatus == 1}">
								<a href="${ctx}/admin/order/deliver?oid=${order.oid}&ostatus=2" role="button" title="订单发货"
								   data-toggle="modal" onclick="confirm('确定要给此订单发货吗？')"><i class="fa fa-truck" aria-hidden="true"></i></a>
							</c:if>

						</td>

					</tr>
				</c:forEach>

				
			</tbody>
		</table>
		<nav aria-label="Page navigation">
			<ul class="pagination">
				<li class="${page.pageIndex == 1 ? 'disabled' : ''}">
					<a href="${ctx}/admin/order?pageIndex=${page.prevPageIndex}&ocode=${param.ocode}&uname=${param.uname}&otimeBein=${param.otimeBegin}&otimeEnd=${param.otimeEnd}&ostatus=${param.ostatus}" aria-label="Previous">
						<span aria-hidden="true">&laquo;</span>
					</a>
				</li>
				<c:forEach begin="1" end="${page.totalPage}" step="1" varStatus="status">
					<li class="${status.count == page.pageIndex ? 'active' : ''}">
						<a href="${ctx}/admin/order?pageIndex=${status.count}&ocode=${param.ocode}&uname=${param.uname}&otimeBein=${param.otimeBegin}&otimeEnd=${param.otimeEnd}&ostatus=${param.ostatus}">${status.count}</a></li>
				</c:forEach>
				<li class="${page.pageIndex == page.totalPage ? 'disabled' : ''}">
					<a href="${ctx}/admin/order?pageIndex=${page.nextPageIndex}&ocode=${param.ocode}&uname=${param.uname}&otimeBein=${param.otimeBegin}&otimeEnd=${param.otimeEnd}&ostatus=${param.ostatus}" aria-label="Next">
						<span aria-hidden="true">&raquo;</span>
					</a>
				</li>
			</ul>
		</nav>

<div class="modal fade bs-example-modal-sm" id="alertModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-sm" role="document" style="width: 85%">
    <div class="modal-content">
      <div class="modal-body">
        <iframe id="orderGoodsList" style="width: 100%;height: calc(100vh - 100px);"></iframe>
      </div>
    </div>
  </div>
</div>

<script src="../resources/bootstrap/js/bootstrap.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		$('#alertModal').modal('hide');
	});
</script>
		<!-- 底部开始 -->
		<%@include file="comms/footer.jsp"%>
		<!-- 底部结束 -->
		<%@include file="comms/message.jsp"%>
		<iframe id="sendEmail" style="display: none"></iframe>
	</body>
</html>

