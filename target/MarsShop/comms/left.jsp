<%--设置 JSP 页面生成的响应的内容类型和页面编码--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="sub-cate">
    <div class=" top-nav rsidebar span_1_of_left">
        <h3 class="cate">商品分类</h3>
        <ul class="menu">
            <ul class="kid-menu ">
                <c:forEach items="${typeList}" var="t" varStatus="status" >
                    <li class="${t.tid == param.tid ? 'active' : ''}"><a href="${ctx}/list.html?tid=${t.tid}"><i class="fa fa-angle-double-right"></i>
                            ${t.tname}</a></li>
                </c:forEach>

            </ul>
        </ul>
    </div>
    <!--initiate accordion-->
    <script type="text/javascript">
        $(function() {
            var menu_ul = $('.menu > li > ul'), menu_a = $('.menu > li > a');
            menu_ul.hide();
            menu_a.click(function(e) {
                e.preventDefault();
                if (!$(this).hasClass('active')) {
                    menu_a.removeClass('active');
                    menu_ul.filter(':visible').slideUp('normal');
                    $(this).addClass('active').next().stop(true, true)
                        .slideDown('normal');
                } else {
                    $(this).removeClass('active');
                    $(this).next().stop(true, true).slideUp('normal');
                }
            });

        });
    </script>
</div>