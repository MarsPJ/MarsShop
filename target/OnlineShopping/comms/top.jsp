<%--设置 JSP 页面生成的响应的内容类型和页面编码--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--JSP页面中使用JSTL <c:...> 标签库的声明语句。它引入了 JSTL Core 库，其中包含了一组用于控制流程和基本数据操作的标签。--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="top-header">
    <div class="container">
        <c:set value="${pageContext.request.requestURI}" var="uri" ></c:set>
        <c:if test="${uri.contains('sign-up') || uri.contains('sign-in')}">
            <div class="top-header-left">
                <a href="${ctx}">
                    <img style="height: 20px"
                         src="${ctx}/resources/images/logowhite.png" />
                </a>
            </div>
        </c:if>
        <div class="top-header-right">
            <div class="down-top">
                <select class="dropdown">
                    <option value="1" class="label" value="">注册</option>
                </select>
            </div>
            <div
                    class="down-top

					">
                <select class="dropdown">
                    <option value="2" class="label" value="">登录</option>
                </select>
            </div>

            <div class="down-top">
                <select class="dropdown">
                    <option value="3" class="label">guobingyan</option>
                    <option>我的订单</option>
                    <option>注销</option>
                </select>
            </div>
            <div class="clearfix"></div>
        </div>
        <div class="clearfix"></div>
    </div>
</div>

<script>
    $(document).ready(function(){
        $("span.selected").click(function() {
            var selected = $(this).text();
            if (selected == '注册') location.href = 'sign-up.jsp';
            if (selected == '登录') location.href = 'sign-in.jsp';

        });
        $("ul li").click(function() {
            var selected = $(this).text();
            if (selected == '我的订单') location.href = 'account/myorder.html';
            if (selected == '注销') location.href = 'sign-out';

        });
    });
</script>
