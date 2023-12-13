package com.marsshop.controller;

import com.marsshop.domain.Goodstype;
import com.marsshop.service.GoodstypeService;
import com.marsshop.service.Impl.GoodstypeServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

/**
 * 进入商品添加页面，要先查询所有商品类别
 */
@WebServlet(name = "GoodsAddPageController", value = "/admin/goods/addpage")
public class GoodsAddPageController extends HttpServlet {
    private GoodstypeService goodstypeService = new GoodstypeServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Goodstype> goodstypeList = goodstypeService.selectAll();
        request.setAttribute("typeList", goodstypeList);
        request.getRequestDispatcher("/admin/goods-add.jsp").forward(request, response);

    }

}
