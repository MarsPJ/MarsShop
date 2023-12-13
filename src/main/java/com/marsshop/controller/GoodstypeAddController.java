package com.marsshop.controller;

import com.marsshop.domain.Goodstype;
import com.marsshop.service.Impl.GoodstypeServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * 添加商品类别控制器
 */

@WebServlet(name = "GoodstypeAddController", value = "/admin/goodstype/add")
public class GoodstypeAddController extends HttpServlet {
    // 添加商品类别业务层对象
    GoodstypeServiceImpl goodstypeService = new GoodstypeServiceImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求参数
        String tname = request.getParameter("tname");
        // 创建类别对象
        Goodstype goodstype = new Goodstype();
        goodstype.setTname(tname);
        // 调用业务层方法实现添加，接收返回值
        int result = goodstypeService.add(goodstype);

        // 如果返回值为1，添加成功，传递提示信息，重定向到商品类别查询
        if (result == 1) {
            HttpSession session = request.getSession();
            session.setAttribute("msg", "商品类别添加成功！");
            response.sendRedirect(request.getContextPath() + "/admin/goodstype");
        } else {// 如果返回值是-1，已存在，添加失败，请求转发到添加商品类别页面，传递提示信息
            request.setAttribute("msg", "商品类别已存在，添加失败！");
            request.getRequestDispatcher("/admin/goodstype-add.jsp").forward(request, response);
        }


    }
}
