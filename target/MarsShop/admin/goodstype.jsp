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

		<h1 class="page-title">类别列表</h1>
		<ul class="breadcrumb">
			<li><a href="#">首页</a></li>
			<li><a href="#">类别管理</a></li>
			<li class="active">类别列表</li>
		</ul>

	</div>
	<div class="main-content">

		<div class="btn-toolbar list-toolbar">
			<a class="btn btn-primary" href="${ctx}/admin/goodstype-add.jsp"><i class="fa fa-plus"></i>
				添加类别</a>
			<a class="btn btn-primary" href="#" onclick="delType()"><i class="fa fa-plus"></i>
				删除</a>
		</div>
		<table class="table">
			<thead>
				<tr>
					<th><input type="checkbox" name="check-all" onclick="checkedAll()"></th>
					<th>#</th>
					<th>类别名称</th>
					<th style="width: 3.5em;"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="type" varStatus="status" >
					<tr>
						<td>
							<%--第二层循环，遍历所有ids，看看是否有id和当前type的id一致--%>
							<c:forEach items="${ids}" var="id">
								<%--如果条件成立，设置名为 checked 的变量为字符串 "checked"--%>
								<c:if test="${id eq type.tid.toString()}" >
									<c:set var="checked" value="checked"></c:set>
								</c:if>
							</c:forEach>
							<%--直接在后面添加设置的变量checked的值，如果没有这个变量则为空，此时不被选中--%>
							<input type="checkbox" name="tid" value="${type.tid}" ${checked}>
							<%--移除之前设置的 checked 变量，以免影响后续循环迭代--%>
							<c:remove var="checked"></c:remove>
						</td>

						<td>${page.firstIndex + status.count}</td>
						<td>${type.tname}</td>
						<td><a href="${ctx}/admin/goodstype/edit?tid=${type.tid}"><i class="fa fa-pencil"></i></a>
						<a href="${ctx}/admin/goodstype/delete?tid=${type.tid}&pageIndex=${page.pageIndex}" role="button" data-toggle="modal"
						onclick="return confirm('确定删除此分类吗？')"><i
								class="fa fa-trash-o"></i></a></td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
		<nav aria-label="Page navigation">
			<ul class="pagination">
				<li class="${page.pageIndex == 1 ? 'disabled' : ''}">
					<a href="${ctx}/admin/goodstype?pageIndex=${page.prevPageIndex}" aria-label="Previous">
						<span aria-hidden="true">&laquo;</span>
					</a>
				</li>
				<c:forEach begin="1" end="${page.totalPage}" step="1" varStatus="status">
					<li class="${status.count == page.pageIndex ? 'active' : ''}"><a href="${ctx}/admin/goodstype?pageIndex=${status.count}">${status.count}</a></li>
				</c:forEach>
				<li class="${page.pageIndex == page.totalPage ? 'disabled' : ''}">
					<a href="${ctx}/admin/goodstype?pageIndex=${page.nextPageIndex}" aria-label="Next">
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
		let checks = document.querySelectorAll('[name="tid"]');
		for (let c of checks) {
			c.checked = all.checked;
		}
	}

	function delType() {
		// 获取所有被选中的复选框对象
		let checks = document.querySelectorAll('[name=tid]:checked');
		// 检查是否有勾选
		if (checks && checks.length == 0) {
			alert("请勾选要删除的商品类别！");
			return false;
		}
		// 拼接url
		if (confirm("确定要删除选择的商品类别吗？")) {
			let param = '';
			for (let c of checks) {
				param += "&tid=" + c.value;
			}
			location.href = '${ctx}/admin/goodstype/delete?pageIndex=' + ${page.pageIndex} + param;
		}
	}
</script>
		<!-- 底部开始 -->
		<%@include file="comms/footer.jsp"%>
		<!-- 底部结束 -->
		<%@include file="comms/message.jsp"%>
	</body>
</html>


