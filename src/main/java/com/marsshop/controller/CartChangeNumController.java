package com.marsshop.controller;

import com.marsshop.domain.Cart;
import com.marsshop.domain.Goods;
import com.marsshop.service.CartService;
import com.marsshop.service.Impl.CartServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

/**
 * 修改购物车商品数量
 */
@WebServlet(name = "CartChangeNumController", value = "/account/cart/changenum.html")
public class CartChangeNumController extends HttpServlet {
    CartService cartService = new CartServiceImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String scid = request.getParameter("scId");
        String scNum = request.getParameter("scNum");
        String gdId = request.getParameter("gdId");
        int result = 1;
        Pattern pattern = Pattern.compile("^\\d+$");
        boolean flag = pattern.matcher(scNum).find();
        // 只有数量是整数时才会修改数量
        if (flag) {
            Cart cart = new Cart();
            cart.setScId(Integer.parseInt(scid));
            cart.setScNum(Integer.parseInt(scNum));
            Goods goods = new Goods();
            goods.setGdId(Integer.parseInt(gdId));
            cart.setGoods(goods);
            result = cartService.updateScNum(cart);
        }
        String ret = "{\"valid\" : "+ (result == 1) + "}";

        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(ret);
        out.flush();

    }
}
