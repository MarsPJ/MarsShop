package com.marsshop.controller;

import com.marsshop.service.Impl.OrderServiceImpl;
import com.marsshop.service.OrderService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "OrderConfirmController", value = "/account/order/confirm.html")
public class OrderConfirmController extends HttpServlet {
    OrderService orderService = new OrderServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oid = request.getParameter("oid");
        orderService.updateStatus(3, Integer.parseInt(oid));

        response.sendRedirect(request.getContextPath() + "/account/order.html");
    }

}
