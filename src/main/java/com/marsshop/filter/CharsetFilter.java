package com.marsshop.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;
//对所有资源的请求都需要经过这个过滤器
@WebFilter(filterName = "CharsetFilter", value = "/*")
public class CharsetFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //设置请求和响应的字符编码
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        chain.doFilter(request, response);
    }
}
