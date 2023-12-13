package com.marsshop.controller;

import com.marsshop.domain.User;
import com.marsshop.service.Impl.UserServiceImpl;
import com.marsshop.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserDetailController", value = "/admin/user/detail")
public class UserDetailController extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _uid = request.getParameter("uid");
        User user = userService.selectByUid(Integer.parseInt(_uid));
        request.setAttribute("user", user);
        request.getRequestDispatcher("/admin/user-detail.jsp").forward(request, response);
    }

}
