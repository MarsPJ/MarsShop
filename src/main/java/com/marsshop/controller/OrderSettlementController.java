package com.marsshop.controller;

import com.marsshop.domain.Goods;
import com.marsshop.domain.User;
import com.marsshop.service.Impl.OrderServiceImpl;
import com.marsshop.service.OrderService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "OrderSettlementController", value = "/account/order/settlement.html")
public class OrderSettlementController extends HttpServlet {
    private OrderService orderService = new OrderServiceImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] _gdId = request.getParameterValues("gdId");
        String[] _scNum = request.getParameterValues("scNum");
        String[] _gdPrice = request.getParameterValues("gdPrice");

        List<Goods> goodsList = new ArrayList<>();
        for (int i = 0; i < _gdId.length; i++) {
            Integer gdId = Integer.parseInt(_gdId[i]);
            Integer scNum = Integer.parseInt(_scNum[i]);
            BigDecimal gdPrice = new BigDecimal(_gdPrice[i]);
            Goods goods = new Goods();
            goods.setGdId(gdId);
            goods.setGdEvNum(scNum);
            goods.setGdPrice(gdPrice);
            goodsList.add(goods);
        }
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("login_user");
        orderService.add(goodsList, user.getUid());
        response.sendRedirect(request.getContextPath() + "/account/order.html");
    }
}
