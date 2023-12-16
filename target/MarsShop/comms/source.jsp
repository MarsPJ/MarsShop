<%--设置 JSP 页面生成的响应的内容类型和页面编码--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<link href="${ctx}/resources/bootstrap/css/bootstrap.css"
      rel="stylesheet" type="text/css" media="all" />
<link rel="stylesheet"
      href="${ctx}/resources/bootstrap/css/font-awesome.css">
<!--theme-style-->
<link href="${ctx}/resources/styles/style.css" rel="stylesheet"
      type="text/css" media="all" />
<!--//theme-style-->
<link rel="stylesheet"
      href="${ctx}/resources/bootstrap/css/bootstrapValidator.min.css" />
<meta name="viewport"
      content="width=device-width, initial-scale=1, maximum-scale=1">
<!--fonts-->
<link href='${ctx}/resources/bootstrap/css/font-gooleapis.css'
      rel='stylesheet' type='text/css'>

<script src="${ctx}/resources/bootstrap/js/jquery.min.js"></script>
<script src="${ctx}/resources/bootstrap/js/bootstrap.js"></script>
<script src="${ctx}/resources/bootstrap/js/jquery.easydropdown.js"></script>
<script
        src="${ctx}/resources/bootstrap/js/bootstrapValidator.min.js"
        type="text/javascript"></script>
<script src="${ctx}/resources/bootstrap/js/jquery.wmuSlider.js"></script>
<script>
    $(()=>{
        $('.example1').wmuSlider();
    })

</script>