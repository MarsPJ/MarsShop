package com.marsshop.controller;

import com.marsshop.domain.Page;
import com.marsshop.domain.User;
import com.marsshop.service.Impl.UserServiceImpl;
import com.marsshop.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserController", value = "/admin/user")
public class UserController extends HttpServlet {
    UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 传入的查询条件
        String param = request.getParameter("param");
        // 当前页
        String _pageIndex = request.getParameter("pageIndex");
        int pageIndex = 1;
        if (_pageIndex != null && !_pageIndex.isEmpty()) {
            pageIndex = Integer.parseInt(_pageIndex);
        }
        Page page = new Page(pageIndex);
        List<User> userList = userService.selectByParam(param, page);
        request.setAttribute("userList", userList);
        request.setAttribute("page", page);
        request.getRequestDispatcher("/admin/user.jsp").forward(request, response);


    }

}
