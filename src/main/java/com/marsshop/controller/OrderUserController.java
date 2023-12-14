package com.marsshop.controller;

import com.marsshop.domain.Order;
import com.marsshop.domain.User;
import com.marsshop.service.Impl.OrderServiceImpl;
import com.marsshop.service.OrderService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderUserController", value = "/account/order.html")
public class OrderUserController extends HttpServlet {
    OrderService orderService = new OrderServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("login_user");
        List<Order> orderList = orderService.selectByUid(user.getUid());

        request.setAttribute("orderList", orderList);

        request.getRequestDispatcher("/account/myorder.jsp").forward(request, response);

    }
}
