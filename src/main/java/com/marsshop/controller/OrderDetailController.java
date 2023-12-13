package com.marsshop.controller;

import com.marsshop.dao.GoodsHistoryDao;
import com.marsshop.domain.Goods;
import com.marsshop.service.Impl.OrderServiceImpl;
import com.marsshop.service.OrderService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderDetailController", value = "/admin/order/detail")
public class OrderDetailController extends HttpServlet {
    private OrderService orderService = new OrderServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oid = request.getParameter("oid");
        List<Goods> goodsList = orderService.selectDetailByOid(Integer.parseInt(oid));
        request.setAttribute("goodsList", goodsList);
        request.getRequestDispatcher("/admin/order-detail.jsp").forward(request, response);
    }

}
