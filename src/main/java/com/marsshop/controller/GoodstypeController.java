package com.marsshop.controller;

import com.marsshop.domain.Goodstype;
import com.marsshop.domain.Page;
import com.marsshop.service.GoodstypeService;
import com.marsshop.service.Impl.GoodstypeServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品类别查询控制器，根据传入的页码得到该页面的商品类别信息，默认页面大小是5
 */
@WebServlet(name = "GoodstypeController", value = "/admin/goodstype")
public class GoodstypeController extends HttpServlet {
    // 商品类别业务层对象
    private GoodstypeServiceImpl goodstypeService = new GoodstypeServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 配合GoodstypeAddController完成消息中转
        HttpSession session = request.getSession();
        String msg = (String) session.getAttribute("msg");
        if (msg != null) {
            // 当商品类别添加成功时，将消息从session域删除并放到request域，使得刷新页面的时候不会反复显示msg的内容
            // 因为request域每次请求都会清空
            session.removeAttribute("msg");
            request.setAttribute("msg", msg);
        }

        // 用于回显删除复选框
        String[] ids = (String[]) session.getAttribute("ids");
        if (ids != null) {
            session.removeAttribute("ids");
            request.setAttribute("ids", ids);
        }

        // 获取请求参数
        String _pageIndex = request.getParameter("pageIndex");
        // 创建page对象
        int pageIndex = 1;
        if (_pageIndex != null && ! _pageIndex.isEmpty()) {
            pageIndex = Integer.valueOf(_pageIndex);
        }

        Page page = new Page(pageIndex);
        List<Goodstype> list = goodstypeService.selectByPage(page);
        // 返回数据
        request.setAttribute("list", list);
        request.setAttribute("page", page);

        // 转发页面
        request.getRequestDispatcher("/admin/goodstype.jsp").forward(request, response);
    }
}
