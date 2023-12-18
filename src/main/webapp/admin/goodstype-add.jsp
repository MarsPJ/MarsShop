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

		<h1 class="page-title">类别添加</h1>
		<ul class="breadcrumb">
			<li>首页</li>
			<li>类别管理</li>
			<li class="active">类别添加</li>
		</ul>

	</div>
	<div class="main-content">
		<div class="row">
			<div class="col-md-5">
				<form id="form1" class="form-horizontal" action="${ctx}/admin/goodstype/add" method="post" onsubmit="return check()">
					<div class="form-group">
						<label class="col-sm-3 control-label">类别名称</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" name="tname" value="${param.tname}"
								placeholder="">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-3 col-sm-10">
							<button class="btn btn-primary"><i class="fa fa-save"></i> 保存</button>
						</div>
					</div>
				</form>
			</div>
		</div>

		<!-- 底部开始 -->
		<%@include file="comms/footer.jsp"%>
		<script>
			function check() {
				let tname = form1.tname.value;
				if (tname == '') {
					alert('名称不能为空！')
					form1.tname.focus();
					return false;
				}
				return true;
			}
		</script>
		<%@include file="comms/message.jsp"%>
	</body>
</html>
