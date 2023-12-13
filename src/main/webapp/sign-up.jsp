<%--设置 JSP 页面生成的响应的内容类型和页面编码--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<title>注册</title>
<%@include file="comms/source.jsp"%>
</head>
<body>
	<div class="header">
		<%@include file="comms/top.jsp"%>
	</div>
	<!-- /head部件 -->

	<div class="register">
		<form class="form-horizontal" method="post" action="${ctx}/sign-up.html">
			<div class="  register-top-grid">
				<div class="form-group">
					<div class="col-sm-2"></div>
					<div class="col-sm-10">
						<h3>
							<i class="fa fa-address-card-o fa-lg fa-fw"></i> 会员注册
						</h3>
					</div>
				</div>
				<div class="mation">
					<div class="form-group">
						<label class="col-sm-2 control-label"><span>用户名*</span></label>
						<div class="col-sm-5">
							<input type="text" name="uname" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label"><span>密码*</span></label>
						<div class="col-sm-5">
							<input type="password" name="upwd" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label"><span>确认密码*</span></label>
						<div class="col-sm-5">
							<input type="password" name="urepwd" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label"><span>性别*</span></label>
						<div class="col-sm-5">
							<label class="checkbox-inline" style="border: 0"> <input
								type="radio" name="usex" checked="checked" value="男" /> 男 <i
								style="margin-left: 5px"></i> 
								<input type="radio" name="usex" value="女"/>
								女
							</label>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label"><span>出生日期*</span></label>
						<div class="col-sm-5">
							<input name="ubirth" type="date"
								style="width: 60%; height: 36px;" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><span>手机*</span></label>
						<div class="col-sm-5">
							<input type="text" name="uphone" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><span>Email*</span></label>
						<div class="col-sm-5">
							<input type="email" name="uemail" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><span>QQ*</span></label>
						<div class="col-sm-5">
							<input type="text" name="uqq" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label"></label>
						<div class="col-sm-10 register-but">
							<!-- <button type="submit" name="submit">注册</button> -->
							<input type="submit" value="注册" />
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>


	<script>
		$(function() {
			let date = new Date();
			let d = date.getFullYear() + '-' + (date.getMonth() + 1).toString().padStart(2, 0) + '-' + date.getDate().toString().padStart(2, 0);
			// console.log(d);
			$('[name="ubirth"]')[0].max = d;
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
							},
							stringLength: {
								min : 6,
								message : '用户名不能小于6个字符'
							},
							threshold: 6, //有6字符以上才发送ajax请求
							remote: {
								url: '${ctx}/sign-up/check.html',
								delay: 1000,   //ajax刷新的时间是1秒/次
								data: function() {
									return {
										uname: $("input[name=uname]").val()
									};
								},
								message: '用户名已存在',
								type: 'post'
							}
						}
					},
					upwd : {
						validators : {
							notEmpty : {
								message : '密码不能为空'
							},
							stringLength: {
								min : 8,
								message : '密码长度至少为8位'
							}
						}
					},
					urepwd : {
						validators : {
							notEmpty : {
								message : '确认密码不能为空'
							},
							identical : {
								field : 'upwd',
								message : '密码和确认密码不一致'
							}
						}
					},
					ubirth : {
						validators : {
							notEmpty : {
								message : '出生日期不能为空'
							}
						}
					},
					uphone : {
						validators : {
							notEmpty : {
								message : '手机不能为空'
							},
							regexp : {
								regexp : /^1\d{10}$/,
								message : '手机号必须是以1开头的11位数字'
							},
							threshold: 11, //有11字符以上才发送ajax请求
							remote: {
								url: '${ctx}/sign-up/check.html',
								delay: 1000,   //ajax刷新的时间是1秒/次
								data: function() {
									return {
										uname: $("input[name=uphone]").val()
									};
								},
								message: '手机号已存在',
								type: 'post'
							}
						}
					},
					uemail : {
						validators : {
							notEmpty : {
								message : '邮箱不能为空'
							},
							emailAddress : {
								message : '不是有效的邮箱格式'
							},
							threshold: 4, //有4字符以上才发送ajax请求
							remote: {
								url: '${ctx}/sign-up/check.html',
								delay: 1000,   //ajax刷新的时间是1秒/次
								data: function() {
									return {
										uname: $("input[name=uemail]").val()
									};
								},
								message: '邮箱已存在',
								type: 'post'
							}
						}
					},
					uqq : {
						validators : {
							notEmpty : {
								message : 'QQ不能为空'
							},
							regexp: {
								regexp : /^\d{5,}$/,
								message : 'QQ号至少为5位数字'
							},
							threshold: 5, //有5字符以上才发送ajax请求
							remote: {
								url: '${ctx}/sign-up/check.html',
								delay: 1000,   //ajax刷新的时间是1秒/次
								data: function() {
									return {
										uname: $("input[name=uqq]").val()
									};
								},
								message: 'qq号已存在',
								type: 'post'
							}
						}
					},
				}
			});
		});
	</script>
</body>
</html>

