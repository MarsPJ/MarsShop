<%--设置 JSP 页面生成的响应的内容类型和页面编码--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--JSP页面中使用JSTL <c:...> 标签库的声明语句。它引入了 JSTL Core 库，其中包含了一组用于控制流程和基本数据操作的标签。--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>商城后台</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="stylesheet" type="text/css"
          href="${ctx}/resources/bootstrap/css/bootstrap.css">
    <link rel="stylesheet"
          href="${ctx}/resources/bootstrap/css/font-awesome.css">

    <script src="${ctx}/resources/bootstrap/js/jquery.min.js"
            type="text/javascript"></script>
    <script src="${ctx}/resources/jQuery-Knob/js/jquery.knob.js"
            type="text/javascript"></script>
    <script type="text/javascript">
        $(function() {
            $(".knob").knob();
        });
    </script>

    <link rel="stylesheet" type="text/css"
          href="${ctx}/resources/bootstrap/css/theme.css">
    <link rel="stylesheet" type="text/css"
          href="${ctx}/resources/bootstrap/css/premium.css">
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

        #gdimage {
            height: 50px;
            width: 50px;
        }
        [aria-label='Page navigation'] {
            display: flex;
            justify-content: center;
        }

    </style>
</head>
<body class=" theme-blue theme-3">


<script type="text/javascript">
    $(function() {
        var uls = $('.sidebar-nav > ul > *').clone();
        uls.addClass('visible-xs');
        $('#main-menu').append(uls.clone());
    });
</script>

<div class="navbar navbar-default" role="navigation">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed"
                data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span> <span
                class="icon-bar"></span> <span class="icon-bar"></span> <span
                class="icon-bar"></span>
        </button>
        <a class="" href="index.html"><span class="navbar-brand">
					<span class="glyphicon glyphicon-shopping-cart"></span> MARSSHOP 商城后台
			</span></a>
    </div>
    <c:if test="${not empty login_admin}" >
        <div class="navbar-collapse collapse" style="height: 1px;">
            <ul id="main-menu" class="nav navbar-nav navbar-right">
                <li class="dropdown hidden-xs"><a href="#"
                                                  class="dropdown-toggle" data-toggle="dropdown"> <span
                        class="glyphicon glyphicon-user padding-right-small"
                        style="position: relative; top: 3px;"></span>${login_admin.aname}<i
                        class="fa fa-caret-down"></i>
                </a>

                    <ul class="dropdown-menu">
                        <li><a tabindex="-1" href="${ctx}/admin/adlogin-out" onclick="return confirm('是否注销登录？')">注销</a></li>
                    </ul></li>
            </ul>

        </div>
    </c:if>

</div>
