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
		<h1 class="page-title">商品列表</h1>
		<ul class="breadcrumb">
			<li>首页</li>
			<li>商品管理</li>
			<li class="active">商品列表</li>
		</ul>
	</div>

	<div class="main-content">
		<div class="btn-toolbar list-toolbar">
				<a class="btn btn-primary" href="${ctx}/admin/goods/addpage"><i class="fa fa-plus"></i>
					添加商品</a>
				<a class="btn btn-primary" href="#" onclick="delGoods()"><i class="fa fa-plus"></i>
					删除</a>
				</div>
			<div class="btn-group">
				<form action="${ctx}/admin/goods" method="get" class="form-inline">
					<div class="form-group">
						<select class="form-control" name="tid">
							<option value="">所有类别</option>
							<c:forEach items="${typeList}" varStatus="status" var="t">
								<option value="${t.tid}" ${param.tid eq t.tid ? 'selected' :''}>${t.tname}</option>
							</c:forEach>
						</select>
					</div>

					<div class="form-group">
						<input class="form-control" style="width: 400px" placeholder="输入商品名称"
							type="text" name="gdName">
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
					<th><input type="checkbox" name="check-all" onclick="checkedAll()"></th>
					<th>#</th>
					<th>商品名称</th>
					<th>商品分类</th>
					<th>价格</th>
					<th>库存</th>
					<th style="width: 3.5em;"></th>
				</tr>
			</thead>
			<tbody>
					<c:forEach items="${goodsList}" varStatus="status" var="g" >
						<tr>
							<td><input type="checkbox" name="gdId" value="${g.gdId}"></td>
							<td>${status.count + page.firstIndex}</td>
							<td><img class="img-thumbnail" id="gdimage"
									 src="${ctx}/upload/${g.gdImage}" />${g.gdName}</td>
							<td>${g.type.tname}</td>
							<td>${g.gdPrice}</td>
							<td>${g.gdQuantity}</td>
							<td><a href="${ctx}/admin/goods/edit?gdId=${g.gdId}"><i
									class="fa fa-pencil"></i></a> <a href="${ctx}/admin/goods/delete?gdId=${g.gdId}&pageIndex=${page.pageIndex}"
																	 role="button" data-toggle="modal" onclick="return confirm('确定要删除此商品吗？')"><i class="fa fa-trash-o"></i></a></td>
						</tr>
					</c:forEach>

				
			</tbody>
		</table>
		<nav aria-label="Page navigation">
			<ul class="pagination">
				<li class="${page.pageIndex == 1 ? 'disabled' : ''}">
					<a href="${ctx}/admin/goods?pageIndex=${page.prevPageIndex}&tid=${param.tid}&gdName=${param.gdName}" aria-label="Previous">
						<span aria-hidden="true">&laquo;</span>
					</a>
				</li>
				<c:forEach begin="1" end="${page.totalPage}" step="1" varStatus="status">
					<li class="${status.count == page.pageIndex ? 'active' : ''}"><a href="${ctx}/admin/goods?pageIndex=${status.count}">${status.count}</a></li>
				</c:forEach>
				<li class="${page.pageIndex == page.totalPage ? 'disabled' : ''}">
					<a href="${ctx}/admin/goods?pageIndex=${page.nextPageIndex}&tid=${param.tid}&gdName=${param.gdName}" aria-label="Next">
						<span aria-hidden="true">&raquo;</span>
					</a>
				</li>
			</ul>
		</nav>

<div class="modal fade bs-example-modal-sm" id="alertModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
      <div class="modal-body">
        <p class="alert alert-danger text-center" role="alert"></p>
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
	function checkedAll() {
		let all = document.querySelector('[name="check-all"]');
		let checks = document.querySelectorAll('[name="gdId"]');
		for (let c of checks) {
			c.checked = all.checked;
		}
	}

	function delGoods() {
		// 获取所有被选中的复选框对象
		let checks = document.querySelectorAll('[name=gdId]:checked');
		// 检查是否有勾选
		if (checks && checks.length == 0) {
			alert("请勾选要删除的商品！");
			return false;
		}
		// 拼接url
		if (confirm("确定要删除选择的商品吗？")) {
			let param = '';
			for (let c of checks) {
				param += "&gdId=" + c.value;
			}
			location.href = '${ctx}/admin/goods/delete?pageIndex=' + ${page.pageIndex} + param;
		}
	}
</script>
		<!-- 底部开始 -->
		<%@include file="comms/footer.jsp"%>
		<!-- 底部结束 -->
		<%@include file="comms/message.jsp"%>

	</body>
</html>

