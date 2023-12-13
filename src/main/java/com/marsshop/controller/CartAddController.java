package com.marsshop.controller;

import com.marsshop.domain.Cart;
import com.marsshop.domain.Goods;
import com.marsshop.domain.User;
import com.marsshop.service.CartService;
import com.marsshop.service.Impl.CartServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 添加购物车控制层
 */
@WebServlet(name = "CartAddController", value = "/account/cart/add.html")
public class CartAddController extends HttpServlet {
    CartService cartService = new CartServiceImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String gdId = request.getParameter("gdId");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("login_user");
        Cart cart = new Cart();
        Goods goods = new Goods();
        goods.setGdId(Integer.parseInt(gdId));
        cart.setGoods(goods);
        cart.setUid(user.getUid());

        int result = cartService.add(cart);
        // 先设置编码
        response.setContentType("application/json; charset=utf-8");
        // 再获取响应流
        PrintWriter out = response.getWriter();
        out.println("{\"code\" : " + result + "}");
        out.flush();
    }
}
