package com.marsshop.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AdminLogoutController", value = "/admin/adlogin-out")
public class AdminLogoutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("admin_login");
        // 设置会话失效，注意这会导致用户登录的session也失效，因为是同一个浏览器
        // session.invalidate();
        // 重定向登录
        response.sendRedirect(request.getContextPath() + "/admin/adlogin.jsp");
    }

}
