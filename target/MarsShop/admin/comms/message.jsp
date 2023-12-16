<%--设置 JSP 页面生成的响应的内容类型和页面编码--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<c:if test="${not empty msg}">
    <script>
        $(()=>{
            alert("${msg}")
        })
    </script>
</c:if>