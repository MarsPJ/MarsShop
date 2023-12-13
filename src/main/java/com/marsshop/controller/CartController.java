package com.marsshop.controller;

import com.marsshop.domain.Cart;
import com.marsshop.domain.User;
import com.marsshop.service.CartService;
import com.marsshop.service.Impl.CartServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CartController", value = "/account/cart.html")
public class CartController extends HttpServlet {
    CartService cartService = new CartServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("login_user");
        List<Cart> cartList = cartService.selectByUid(user.getUid());
        request.setAttribute("cartList", cartList);
        request.getRequestDispatcher("/account/cart.jsp").forward(request, response);


    }
}
