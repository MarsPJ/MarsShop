package com.marsshop.controller;

import com.marsshop.domain.Goods;
import com.marsshop.domain.Goodstype;
import com.marsshop.service.GoodsService;
import com.marsshop.service.GoodstypeService;
import com.marsshop.service.Impl.GoodsServiceImpl;
import com.marsshop.service.Impl.GoodstypeServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GoodsEditController", value = "/admin/goods/edit")
public class GoodsEditController extends HttpServlet {
    GoodsService goodsService = new GoodsServiceImpl();
    GoodstypeService goodstypeService = new GoodstypeServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _gdId = request.getParameter("gdId");

        Goods goods = goodsService.selectByGdId(Integer.parseInt(_gdId));

        List<Goodstype> goodstypeList = goodstypeService.selectAll();

            System.out.println("size: " + goodstypeList.size());
        request.setAttribute("goods", goods);
        request.setAttribute("typeList", goodstypeList);
        request.getRequestDispatcher("/admin/goods-edit.jsp").forward(request, response);


    }
}
