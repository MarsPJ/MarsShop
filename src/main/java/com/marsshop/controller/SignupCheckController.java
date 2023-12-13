package com.marsshop.controller;

import com.marsshop.domain.User;
import com.marsshop.service.Impl.UserServiceImpl;
import com.marsshop.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 会员注册信息重复验证
 */
@WebServlet(name = "SignupCheckController", value = "/sign-up/check.html")
public class SignupCheckController extends HttpServlet {
    UserService userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uname = request.getParameter("uname");
        String uphone = request.getParameter("uphone");
        String uemail = request.getParameter("uemail");
        String uqq = request.getParameter("uqq");
        User user = new User();
        user.setUname(uname);
        user.setUphone(uphone);
        user.setUemail(uemail);
        user.setUqq(uqq);

        boolean result = userService.existsByUser(user);

        response.setContentType("application/json; charset=utf-8");

        PrintWriter out = response.getWriter();
        out.write("{ \"valid\" : " + !result + "}");
        out.flush();
    }
}
