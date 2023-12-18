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
<div class="content" style="margin-left: 0">
        <table class="table">
            <thead>
            <tr>
                <th>#</th>
                <th>商品名称</th>
                <th>商品分类</th>
                <th>价格</th>
                <th>数量</th>
                <th>小计</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${goodsList}" varStatus="status" var="g" >
                <tr>
                    <td>${status.count}</td>
                    <td><img class="img-thumbnail" id="gdImage"
                             src="${ctx}/upload/${g.gdImage}" />${g.gdName}</td>
                    <td>${g.type.tname}</td>
                    <td>${g.gdPrice}</td>
                    <td>${g.gdEvNum}</td>
                    <td>${g.gdTotal}</td>
                </tr>
            </c:forEach>


            </tbody>
        </table>
        <script type="text/javascript">
            $(function() {
                $('#alertModal').modal('hide');
            });
        </script>
</div>
</body>
        </html>


