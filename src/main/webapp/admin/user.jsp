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
		<h1 class="page-title">会员列表</h1>
		<ul class="breadcrumb">
			<li>首页</li>
			<li>会员管理</li>
			<li class="active">会员列表</li>
		</ul>
	</div>

	<div class="main-content">
		<div class="btn-toolbar list-toolbar">
			<div class="btn-group">
				<form action="${ctx}/admin/user" method="get" class="form-inline">
					<div class="form-group">
						<input class="form-control" style="width: 300px" placeholder="输入用户名或者手机"
							type="text" name="param" value="${param.param}"/>
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
					<th>用户名</th>
					<th>性别</th>
					<th>手机</th>
					<th>Email</th>
					<th>注册日期</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${userList}" var="user" varStatus="status">
					<tr>
						<td>${status.count + page.firstIndex}</td>
						<td><a href="${ctx}/admin/user/detail?uid=${user.uid}" title="点击查看会员全部信息">${user.uname}</a></td>
						<td>${user.usex}</td>
						<td>${user.uphone}</td>
						<td>${user.uemail}</td>
						<td><fmt:formatDate value="${user.uregTime}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<nav aria-label="Page navigation">
			<ul class="pagination">
				<li class="${page.pageIndex == 1 ? 'disabled' : ''}">
					<a href="${ctx}/admin/user?pageIndex=${page.prevPageIndex}&param=${param.param}" aria-label="Previous">
						<span aria-hidden="true">&laquo;</span>
					</a>
				</li>
				<c:forEach begin="1" end="${page.totalPage}" step="1" varStatus="status">
					<li class="${status.count == page.pageIndex ? 'active' : ''}">
						<a href="${ctx}/admin/user?pageIndex=${status.count}&param=${param.param}">${status.count}</a></li>
				</c:forEach>
				<li class="${page.pageIndex == page.totalPage ? 'disabled' : ''}">
					<a href="${ctx}/admin/user?pageIndex=${page.nextPageIndex}&param=${param.param}" aria-label="Next">
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
</script>
		<!-- 底部开始 -->
		<%@include file="comms/footer.jsp"%>
		<!-- 底部结束 -->
		<%@include file="comms/message.jsp"%>
	</body>
</html>

