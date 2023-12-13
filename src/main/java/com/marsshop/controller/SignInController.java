package com.marsshop.controller;

import com.marsshop.domain.User;
import com.marsshop.service.Impl.UserServiceImpl;
import com.marsshop.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * 会员登录
 */
@WebServlet(name = "SignInController", value = "/sign-in.html")
public class SignInController extends HttpServlet {
    UserService userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uname = request.getParameter("uname");
        String upwd = request.getParameter("upwd");

        User user = userService.selectByUnameAndUpwd(uname, upwd);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("login_user", user);
            response.sendRedirect(request.getContextPath());
        } else {
            request.setAttribute("msg", "用户名或密码错误！");
            request.getRequestDispatcher("/sign-in.jsp").forward(request, response);
        }

    }
}
