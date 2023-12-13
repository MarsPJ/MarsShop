package com.marsshop.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AdminLoginFilter", value = "/admin/*")
public class AdminLoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        // 获取会话
        HttpSession session = req.getSession();
        // 获取请求的资源路径
        String baseUrl = req.getContextPath();
        String path = req.getRequestURI();
        // 判断路径，是进入登陆页面或者提交登录，放行
        if (path.startsWith(baseUrl + "/admin/login") || path.startsWith(baseUrl + "/admin/adlogin.jsp")) {
            chain.doFilter(request, response);
            return;
        }
        //没有session
        if (session.getAttribute("login_admin") == null) {
            resp.sendRedirect(req.getContextPath() + "/admin/adlogin.jsp");
            return;
        }
        // session通过，进入功能
        chain.doFilter(request, response);
    }
}
