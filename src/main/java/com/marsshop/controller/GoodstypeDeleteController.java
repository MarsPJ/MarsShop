package com.marsshop.controller;

import com.marsshop.service.GoodstypeService;
import com.marsshop.service.Impl.GoodstypeServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "GoodstypeDeleteController", value = "/admin/goodstype/delete")
public class GoodstypeDeleteController extends HttpServlet {
    GoodstypeService goodstypeService = new GoodstypeServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取参数
        String[] _tids = request.getParameterValues("tid");
        String _pageIndex = request.getParameter("pageIndex");

        // 获得删除结果
        Integer[] tids = new Integer[_tids.length];
        for (int i = 0; i < tids.length; i++) {
            tids[i] = Integer.parseInt(_tids[i]);
        }
        int result = goodstypeService.delete(tids);

        HttpSession session = request.getSession();
        String param = "";
        // 删除失败，有该类别商品，回显错误信息，重定向时跳转回用户原来进行删除操作的页面
        if (result == -1) {
            session.setAttribute("msg", "该商品类别下已经添加商品，不能删除！");

            // 用于回显复选框
            session.setAttribute("ids", _tids);
            param = "?pageIndex=" + _pageIndex;
        } else if (result == 1) {
            // 删除成功，回显删除信息，重定向回到商品种类页面
            session.setAttribute("msg", "成功删除商品类别！");
        }
        // 重定向
        response.sendRedirect(request.getContextPath() + "/admin/goodstype" + param);
    }

}
