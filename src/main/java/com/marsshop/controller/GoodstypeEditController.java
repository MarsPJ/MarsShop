package com.marsshop.controller;

import com.marsshop.domain.Goodstype;
import com.marsshop.service.GoodstypeService;
import com.marsshop.service.Impl.GoodstypeServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "GoodstypeEditController", value = "/admin/goodstype/edit")
public class GoodstypeEditController extends HttpServlet {
    GoodstypeService goodstypeService = new GoodstypeServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tid = request.getParameter("tid");
        Goodstype goodstype = goodstypeService.selectById(Integer.parseInt(tid));

        request.setAttribute("type", goodstype);
        request.getRequestDispatcher("/admin/goodstype-edit.jsp").forward(request, response);
    }
}
