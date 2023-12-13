package com.marsshop.controller;

import com.marsshop.domain.Goods;
import com.marsshop.domain.Goodstype;
import com.marsshop.domain.Page;
import com.marsshop.service.GoodsService;
import com.marsshop.service.GoodstypeService;
import com.marsshop.service.Impl.GoodsServiceImpl;
import com.marsshop.service.Impl.GoodstypeServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "IndexController", value = "/index.html")
public class IndexController extends HttpServlet {
    GoodsService goodsService = new GoodsServiceImpl();
    GoodstypeService goodstypeService = new GoodstypeServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Goodstype> goodstypeList = goodstypeService.selectAll();
        Page page = new Page(1, 12);
        List<Goods> goodsList = goodsService.selectByParam(null, null, page);
        request.setAttribute("typeList", goodstypeList);
        request.setAttribute("goodsList", goodsList);
        request.getRequestDispatcher("/index.jsp").forward(request, response);

    }
}
