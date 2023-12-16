<%--设置 JSP 页面生成的响应的内容类型和页面编码--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<!-- top部件 -->
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>MarsShop</title>
	<%@include file="../comms/source.jsp"%>
	<!--script-->
</head>
<body>
<!--top-header-->
<div class="header">
	<%@include file="../comms/top.jsp"%>
	<%@include file="../comms/header.jsp"%>
</div>
<!--/top-header-->

<!-- /top部件 -->

<div class="container">
	<div class="col-sm-1"></div>
	<div class="col-sm-10">
		<div class="cart">
			<form class="form-horizontal" method="post" action="${ctx}/account/order/settlement.html">
				<div class="col-sm-12">
					<h3>
						<i class="fa fa-shopping-cart"></i> 购物车
					</h3>
					<table class="table">
						<tr>
							<th>商品</th>
							<th>价格</th>
							<th>数量</th>
							<th>小计</th>
							<th></th>
						</tr>
						<c:forEach items="${cartList}" var="cart" varStatus="satus">
							<tr>
								<td><img style="width: 50px; height: 50px"
										 class="img-thumbnail" id="gdimage"
										 src="${ctx}/upload/${cart.goods.gdImage}" />${cart.goods.gdName}
									<input type="hidden" name="gdId" value="${cart.goods.gdId}" />
									<input type="hidden" name="gdPrice" value="${cart.goods.gdPrice}" />
								</td>
								<td>¥ <span class="price">${cart.goods.gdPrice}</span></td>
								<td>
									<div class="form-group">
										<input type="text" class="form-control" style="width: 50px" autocomplete="false"
											   name="scNum" value="${cart.scNum}" data-scid="${cart.scId}" data-gdid="${cart.goods.gdId}"/>
									</div>
								</td>
								<td>¥ <span class="subtotal">1497.00</span></td>
								<td><a href="${ctx}/account/cart/delete.html?scId=${cart.scId}" onclick="return confirm('确定要将此商品从购物车删除吗？')"><i
										class="fa fa-trash-o fa-lg" aria-hidden="true"></i></a></td>
							</tr>
						</c:forEach>

						
						<tr><th colspan="5"></th></tr>
					</table>
					<div>
						<div class="col-sm-10"><h4>总价</h4></div>
						<div class="col-sm-2"><h4>¥ <span class="total"></span></h4>
						<input type="hidden" name="ototal" value="" /></div>
					</div>
					<div>
						<label class="col-sm-10 control-label"></label>
						<div class="col-sm-2 account-but">
							<input type="submit" value="结算" />
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<!---->
<%@include file="../comms/footer.jsp"%>
<!---->
<%@include file="../comms/modal.jsp"%>
</body>

<script type="text/javascript">
	$(function() {
		computeTotal();

		$('form').bootstrapValidator({
			feedbackIcons : {
				valid : ' ',
				invalid : ' ',
				validating : 'glyphicon glyphicon-refresh'
			},
			container : 'tooltip',
			fields : {
				'scNum' : {
					validators : {
						notEmpty : {
							message : '不能为空'
						},
						digits : {
							message : '只能输入数字'
						},
						//大于等于1
						greaterThan : {
							value : 1,
							message : '购买数量不能小于1'
						},
						remote : {
							url : '${ctx}/account/cart/changenum.html',
							type : 'post',
							delay : 1000,
							data : function (validator) {
								return {
									scId : input.dataset.scid,
									scNum : input.value,
									gdId : input.dataset.gdid
								}
							},
							message : '输入数量超出该商品库存'
						}
					}
				}
			}
		}).on('success.field.bv', function(e, data) { //验证通过后，重新计算小计值
			computeTotal();
		}).on('status.field.bv', function (e, data){
			input = e.target;
		});
	});

	function computeTotal() {
		var total = 0.00;
		//遍历所有name为scnum的input标签，index为每个input标签的索引
		$("input[name='scNum']").each(function(index) {
			//获取第index个class为price的标签的值
			var price = $(".price").eq(index).text();
			//获取当前input标签的value值
			var num = $(this).val();
			//给第index个class为subtotal的标签文本赋值
			$(".subtotal").eq(index).text((price * num).toFixed(2));
			//计算总价
			total = total + parseFloat($(".subtotal").eq(index).text());
		});
		$(".total").text(total.toFixed(2));
		//赋值给隐藏控件
		$("input[name='ototal']").val(total.toFixed(2));
	}
</script>
</html>