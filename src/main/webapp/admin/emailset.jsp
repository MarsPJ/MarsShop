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

		<h1 class="page-title">邮箱设置</h1>
		<ul class="breadcrumb">
			<li>首页</li>
			<li>系统管理</li>
			<li class="active">邮件设置</li>
		</ul>

	</div>
	<div class="main-content">
		<div class="row">
			<div class="col-md-5">
				<form class="form-horizontal" action="${ctx}/admin/emailset/edit" method="post">
					<c:if test="${not empty email and email.emId != null}">
						<input type="hidden" name="emId" value="${email.emId}">
					</c:if>
					<div class="form-group">
						<label class="col-sm-4 control-label">发送邮件服务器</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" name="emServer"
								value="${email.emServer}" required>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">发件邮件地址</label>
						<div class="col-sm-8">
							<%--TODO:邮箱地址正则无法使用--%>
							<input type="text" class="form-control" name="emAddress"
								value="${email.emAddress}" required>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">授权码</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" name="emPassCode"
								value="${email.emPassCode}" required>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-4 col-sm-10">
							<button class="btn btn-primary"><i class="fa fa-save"></i> 保存</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		

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