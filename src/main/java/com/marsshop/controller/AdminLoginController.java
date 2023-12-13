package com.marsshop.controller;

import com.marsshop.domain.Admin;
import com.marsshop.service.Impl.AdminServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * 后台用户登陆控制器
 */
@WebServlet(name = "AdminLoginController", value = "/admin/login")
public class AdminLoginController extends HttpServlet {
    // admin业务层对象
    private AdminServiceImpl adminService = new AdminServiceImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求参数
        String aname = request.getParameter("aname");
        String apwd = request.getParameter("apwd");
        // 调用业务层方法获得admin对象
        Admin admin = adminService.selectByNameAndPwd(aname, apwd);

        // 如果admin为null，则登陆失败，添加提示信息，并返回（转发）登陆页面
        if (admin == null) {
            // 因为是使用请求转发回到登陆界面，所以是把属性放到request中而不是response
            request.setAttribute("msg", "用户名或密码错误");
            // 请求转发
            request.getRequestDispatcher("/admin/adlogin.jsp").forward(request, response);
        } else { // 否则将用户添加到会话中，重定向到商品分类查询
            HttpSession session = request.getSession();
            session.setAttribute("login_admin", admin);
            // 获取根目录
            String rootPath = request.getContextPath();
            // 重定向
            response.sendRedirect(rootPath + "/admin/goodstype");
        }

    }
}
