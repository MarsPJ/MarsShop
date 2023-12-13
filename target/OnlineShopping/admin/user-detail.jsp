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

        <h1 class="page-title">会员信息</h1>
        <ul class="breadcrumb">
            <li><a href="#">首页</a></li>
            <li><a href="#">会员管理</a></li>
            <li class="active">会员信息</li>
        </ul>

    </div>
    <div class="main-content">
        <div class="row">
            <div class="col-md-5">
                <form id="form1" class="form-horizontal" method="post">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">会员id</label>
                        <div class="col-sm-9">
                            ${user.uid}
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">姓名</label>
                        <div class="col-sm-9">
                            ${user.uname}
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">性别</label>
                        <div class="col-sm-9">
                            ${user.usex}
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">生日</label>
                        <div class="col-sm-9">
                            <fmt:formatDate value="${user.ubirth}" pattern="yyyy-MM-dd"></fmt:formatDate>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">手机号</label>
                        <div class="col-sm-9">
                            ${user.uphone}
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">邮箱</label>
                        <div class="col-sm-9">
                            ${user.uemail}
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">QQ</label>
                        <div class="col-sm-9">
                            ${user.uqq}
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">头像</label>
                        <div class="col-sm-9">
                            <img src="${ctx}/upload/${user.uimage}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">会员积分</label>
                        <div class="col-sm-9">
                            ${user.ucredit}
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">注册时间</label>
                        <div class="col-sm-9">
                            <fmt:formatDate value="${user.uregTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <!-- 底部开始 -->
        <%@include file="comms/footer.jsp"%>
        </body>
        </html>