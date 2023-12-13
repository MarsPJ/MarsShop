package com.marsshop.controller;

import com.marsshop.service.Impl.OrderServiceImpl;
import com.marsshop.service.OrderService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "OrderDeliverController", value = "/admin/order/deliver")
public class OrderDeliverController extends HttpServlet {
    OrderService orderService = new OrderServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _oid = request.getParameter("oid");
        String _ostatus = request.getParameter("ostatus");
        orderService.updateStatus(Integer.parseInt(_ostatus), Integer.parseInt(_oid));
        HttpSession session = request.getSession();
        session.setAttribute("msg", "商品发货成功！");
        response.sendRedirect(request.getContextPath() + "/admin/order");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
