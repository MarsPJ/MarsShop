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

@WebServlet(name = "GoodsController", value = "/admin/goods")
public class GoodsController extends HttpServlet {
    private GoodsService goodsService = new GoodsServiceImpl();
    private GoodstypeService goodstypeService = new GoodstypeServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String msg = (String) session.getAttribute("msg");

        if (msg != null) {
            session.removeAttribute("msg");
            request.setAttribute("msg", msg);
        }

        String _tid = request.getParameter("tid");
        String gdName = request.getParameter("gdName");
        String _pageIndex = request.getParameter("pageIndex");

        Integer tid = null;
        Integer pageIndex = 1;
        //选择商品类别查询
        if (_tid != null && !_tid.isEmpty()) {
            tid = Integer.parseInt(_tid);
        }

        if (_pageIndex != null && !_pageIndex.isEmpty()) {
            pageIndex = Integer.parseInt(_pageIndex);
        }

        Page page = new Page(pageIndex);

        List<Goodstype> goodstypeList = goodstypeService.selectAll();
        List<Goods> goodsList = goodsService.selectByParam(tid, gdName, page);

        request.setAttribute("goodsList", goodsList);
        request.setAttribute("typeList", goodstypeList);
        request.setAttribute("page", page);
        request.getRequestDispatcher("/admin/goods.jsp").forward(request, response);
    }
}
