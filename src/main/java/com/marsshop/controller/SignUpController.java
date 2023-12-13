package com.marsshop.controller;

import com.marsshop.domain.User;
import com.marsshop.service.Impl.UserServiceImpl;
import com.marsshop.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 保存新注册用户的信息
 */
@WebServlet(name = "SignUpController", value = "/sign-up.html")
public class SignUpController extends HttpServlet {
    UserService userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uname = request.getParameter("uname");
        String upwd = request.getParameter("upwd");
        String usex = request.getParameter("usex");
        String ubirth = request.getParameter("ubirth");
        String uphone = request.getParameter("uphone");
        String uemail = request.getParameter("uemail");
        String uqq = request.getParameter("uqq");

        User user = new User();

        user.setUname(uname);
        user.setUpwd(upwd);
        user.setUsex(usex);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            user.setUbirth(sdf.parse(ubirth));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setUphone(uphone);
        user.setUemail(uemail);
        user.setUqq(uqq);

        // 注册成功
        userService.save(user);
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("<script>alert('注册成功，请登录');" +
                "location.href='" + request.getContextPath() + "/sign-in.jsp'</script>");
        out.flush();
        // 重定向到登录界面
        // response.sendRedirect(request.getContextPath() + "/sign-in.jsp");
    }
}
