<%--设置 JSP 页面生成的响应的内容类型和页面编码--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="bottom-header">
    <div class="container">
        <div class="col-md-3">
            <div class="logo">
                <a href="${ctx}"><img style="width: 180px"
                                     src="${ctx}/resources/images/logo5.png" alt=" " /></a>
            </div>
        </div>
        <div class="col-md-6">
            <form action="${ctx}/list.html" method="get">
                <div class="input-group" style="margin-top: 30px;">
                    <input type="text" name="gdName" value="${param.gdName}" class="form-control" placeholder="请输入商品名称...">
                    <span class="input-group-btn">
							<button class="btn btn-default" type="submit">
								<i class="fa fa-search"></i>
							</button>
						</span>
                </div></form>
            <!-- /input-group -->
        </div>
        <div class="col-md-3">
            <ul class="men-grid">
                <li class="cart"><a href="javascript:void(0)" onclick="toCart()"><span>
							</span>我的购物车</a></li>
            </ul>
        </div>
        <div class="clearfix"></div>
        <script>
            function toCart() {
                if ('${login_user}' == '') {
                    modal.find('p').html('请先登录');
                    modal.modal('show');
                    return;
                }
                location.href = '${ctx}/account/cart.html';

            }
        </script>
    </div>
</div>