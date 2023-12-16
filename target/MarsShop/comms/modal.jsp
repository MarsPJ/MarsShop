<%--设置 JSP 页面生成的响应的内容类型和页面编码--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="modal fade bs-example-modal-sm" id="alertModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <p class="alert alert-danger text-center" role="alert">操作成功！</p>
            </div>
        </div>
    </div>
</div>
<script src="resources/bootstrap/js/bootstrap.js"
        type="text/javascript"></script>
<script type="text/javascript">
    $(function() {
        // 全局变量
        modal = $('#alertModal');
        // $('#alertModal').modal('show');
    });
</script>