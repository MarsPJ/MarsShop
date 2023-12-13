package com.marsshop.controller;

import com.marsshop.service.CartService;
import com.marsshop.service.Impl.CartServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CartDeleteController", value = "/account/cart/delete.html")
public class CartDeleteController extends HttpServlet {
    private CartService cartService = new CartServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String scId = request.getParameter("scId");
        cartService.delete(Integer.parseInt(scId));

        response.sendRedirect(request.getContextPath() + "/account/cart.html");

    }

}
