<%--设置 JSP 页面生成的响应的内容类型和页面编码--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<title>登录</title>
	<%@include file="comms/source.jsp"%>
</head>
<body>
	<div class="header">
		<%@include file="comms/top.jsp"%>
	</div>
	<!-- /head部件 -->

	<div class="register">
		<form class="form-horizontal" method="post" action="${ctx}/sign-in.html">
			<div class="  register-top-grid">
				<div class="form-group">
					<div class="col-sm-2"></div>
					<div class="col-sm-10">
						<h3>
							<i class="fa fa-address-card-o fa-lg fa-fw"></i> 会员登录
						</h3>
					</div>
				</div>
				<div class="mation">
					<div class="row">
						<label class="col-sm-2"></label>
						<div class="col-sm-5">
							<div id="alert-info" class="alert alert-danger hide" role="alert"><i class="fa fa-info-circle">${msg}</i> </div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><span>用户名*</span></label>
						<div class="col-sm-5">
							<input type="text" name="uname" value="${param.uname}"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><span>密码*</span></label>
						<div class="col-sm-5">
							<input type="password" name="upwd" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label"></label>
						<div class="col-sm-10 register-but">
							<input type="submit" value="登录" />
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>


	<script>
		$(function() {
 			if ('${msg}' != '') {
				$('#alert-info').addClass("show");
			} 
			
			$('form').bootstrapValidator({
				message : 'This value is not valid',
				feedbackIcons : {
					valid : 'glyphicon glyphicon-ok',
					invalid : 'glyphicon glyphicon-remove',
					validating : 'glyphicon glyphicon-refresh'
				},
				fields : {
					uname : {
						validators : {
							notEmpty : {
								message : '用户名不能为空'
							}
						}
					},
					upwd : {
						validators : {
							notEmpty : {
								message : '密码不能为空'
							}
						}
					}
				}
			});
		});
	</script>
</body>
</html>

