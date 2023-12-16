<%--设置 JSP 页面生成的响应的内容类型和页面编码--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--JSP页面中使用JSTL <c:...> 标签库的声明语句。它引入了 JSTL Core 库，其中包含了一组用于控制流程和基本数据操作的标签。--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>商城后台</title>
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<link rel="stylesheet" type="text/css" href="${ctx}/resources/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="${ctx}/resources/bootstrap/css/font-awesome.css">
<script src="${ctx}/resources/bootstrap/js/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/resources/bootstrap/js/bootstrap.js" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/bootstrap/css/theme.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/bootstrap/css/premium.css">

</head>
<body class=" theme-blue theme-3">

	<style type="text/css">
#line-chart {
	height: 300px;
	width: 800px;
	margin: 0px auto;
	margin-top: 1em;
}

.navbar-default .navbar-brand, .navbar-default .navbar-brand:hover {
	color: #fff;
}
</style>

	<script type="text/javascript">
		$(function() {
			var uls = $('.sidebar-nav > ul > *').clone();
			uls.addClass('visible-xs');
			$('#main-menu').append(uls.clone());
		});
	</script>


	<div class="navbar navbar-default" role="navigation">
		<div class="navbar-header">
			<span class="navbar-brand"><span
					class="glyphicon glyphicon-shopping-cart"></span> MARSSHOP 商城后台</span>
		</div>

		<div class="navbar-collapse collapse" style="height: 1px;"></div>
	</div>
	</div>



	<div class="dialog">
		<div class="panel panel-default">
			<p class="panel-heading no-collapse">管理员登录</p>
			<div class="panel-body">
				<%--onsubmit=" return check() 根据check函数的返回来决定是否要提交--%>
				<form id="form1" action="${ctx}/admin/login" method="post" onsubmit=" return check()">
					<div class="form-group">
						<%--添加value，登录错误信息回显--%>
						<label>用户名</label> <input name="aname" type="text" value="${param.aname}" placeholder="请输入用户名" class="form-control span12">
					</div>
					<div class="form-group">
						<label>密码</label> <input type="password" name="apwd" placeholder="请输入密码"
							class="form-controlSIspan12 form-control">
					</div>
					<button type="submit" class="btn btn-primary pull-right">登录</button>
					<label class="remember-me"><font color="red"></font></label>
					<div class="clearfix"></div>
				</form>
			</div>
		</div>
		
	</div>

	<script type="text/javascript">
		$("[rel=tooltip]").tooltip();
		$(function() {
			$('.demo-cancel-click').click(function() {
				return false;
			});
		});
		function check() {
			let aname = form1.aname.value;
			let apwd = form1.apwd.value;
			if (aname == "") {
				alert('用户名不能为空');
				form1.aname.focus();
				return false;
			}
			if (apwd == "") {
				alert('密码不能为空');
				form1.aname.focus();
				return false;
			}
			return true;
		}

	</script>
	
	<%@include file="comms/message.jsp"%>


</body>
</html>
