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

/**
 * 客户网页商品查询
 */
@WebServlet(name = "ListController", value = "/list.html")
public class ListController extends HttpServlet {
    GoodsService goodsService = new GoodsServiceImpl();
    GoodstypeService goodstypeService = new GoodstypeServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _tid = request.getParameter("tid");
        String gdName = request.getParameter("gdName");
        String _pageIndex = request.getParameter("pageIndex");
        Integer tid = null;

        if (_tid != null && !_tid.isEmpty()) {
            tid = Integer.parseInt(_tid);
        }
        Integer pageIndex = 1;
        if (_pageIndex != null && !_pageIndex.isEmpty()) {
            pageIndex = Integer.parseInt(_pageIndex);
        }
        Page page = new Page(pageIndex, 9);
        List<Goodstype> goodstypeList = goodstypeService.selectAll();
        List<Goods> goodsList = goodsService.selectByParam(tid, gdName, page);
        request.setAttribute("goodsList", goodsList);
        request.setAttribute("page", page);
        request.setAttribute("typeList", goodstypeList);
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }

}
