
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

		<h1 class="page-title">商品添加</h1>
		<ul class="breadcrumb">
			<li>首页</li>
			<li>商品管理</li>
			<li class="active">商品添加</li>
		</ul>

	</div>
	<div class="main-content">
		<div class="row">
			<div class="col-md-11">
				<form class="form-horizontal" action="${ctx}/admin/goods/add" method="post" enctype="multipart/form-data" onsubmit="return !isOverSize">
					<div class="form-group">
						<label class="col-sm-2 control-label">商品名称</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="gdName" value="${goods.gdName}"
								placeholder="" required maxlength="100">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">所属类别</label>
						<div class="col-sm-3">
							<select class="form-control" name="tid">
								<c:forEach items="${typeList}" var="t">
									<option value="${t.tid}" ${goods.type.tid eq t.tid ? 'selected' : ''}>${t.tname}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">货号</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="gdCode"
								<%--   定义货号格式，只能是10位数字--%>
								placeholder="4901990000" required pattern="^\d{7}$" maxlength="10" value="${goods.gdCode}">
						</div>
						<label class="col-sm-2 control-label">销售价格</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" name="gdPrice"
								value="${empty goods ? '0.01' : goods.gdPrice}" pattern="^\d+[.]\d{2}$" required>
						</div>
					</div>
					<div class="form-group">
					<label class="col-sm-2 control-label">库存</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="gdQuantity"
								value="${empty goods ? '0' : goods.gdQuantity}" pattern="^[0-9]+$" required>
						</div>
						<label class="col-sm-2 control-label">发货城市</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" name="gdCity" maxlength="50" value="${goods.gdCity}"
								placeholder="北京">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">商品图片</label>
						<div class="col-sm-5">
							<input type="file" class="form-control" name="gdImage" accept="image/*" onchange="checkFileSize(event)" required/>
						</div>
												
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">商品描述</label>
						<div class="col-sm-10">
							<textarea class="form-control" rows="7" name="gdInfo">${goods.gdInfo}</textarea>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-12">
							<button class="btn btn-primary">
								<i class="fa fa-save"></i> 保存
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<script>
			// 因为是在提交表单的时候检查，所以设置为全局变量
			let isOverSize = false;
			function checkFileSize(e) {
				isOverSize = false;
				let file = e.currentTarget.files[0];
				if (file.size > 2 * 1024 * 1024) {
					alert("图片大小不能超过2M！")
					isOverSize = true;
				}
			}
		</script>
		<!-- 底部开始 -->
		<%@include file="comms/footer.jsp"%>
		<!-- 底部结束 -->
		<%@include file="comms/message.jsp"%>

	</body>
</html>