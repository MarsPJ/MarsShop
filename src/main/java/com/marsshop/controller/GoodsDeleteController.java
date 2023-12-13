package com.marsshop.controller;

import com.marsshop.service.GoodsService;
import com.marsshop.service.Impl.GoodsServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "GoodsDeleteController", value = "/admin/goods/delete")
public class GoodsDeleteController extends HttpServlet {
    GoodsService goodsService = new GoodsServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] _gdIds = request.getParameterValues("gdId");
        Integer[] gdIds = new Integer[_gdIds.length];

        if (_gdIds != null && _gdIds.length > 0) {
            for (int i = 0; i < _gdIds.length; i++) {
                gdIds[i] = Integer.parseInt(_gdIds[i]);
            }
        }

        goodsService.delete(gdIds);

        HttpSession session = request.getSession();
        session.setAttribute("msg", "商品删除成功！");
        response.sendRedirect(request.getContextPath() + "/admin/goods");


    }

}
