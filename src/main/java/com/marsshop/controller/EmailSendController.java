package com.marsshop.controller;

import com.marsshop.domain.EmailSet;
import com.marsshop.domain.User;
import com.marsshop.service.EmailSetService;
import com.marsshop.service.Impl.EmailSetServiceImpl;
import com.marsshop.service.Impl.UserServiceImpl;
import com.marsshop.service.UserService;
import com.marsshop.util.EmailUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "EmailSendController", value = "/admin/email/send")
public class EmailSendController extends HttpServlet {
    EmailSetService emailSetService = new EmailSetServiceImpl();
    UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmailSet emailSet = emailSetService.select();
        String uid = request.getParameter("uid");
        String ocode = request.getParameter("ocode");
        String ostatus = request.getParameter("ostatus");
        User user = userService.selectByUid(Integer.parseInt(uid));
        String content ="<h2>尊敬的" + user.getUname() + ":<h2>"; //邮件内容
        String title = "";
        switch (ostatus) {
            case "1": //已下单
                title ="您的订单已收到";
                content += "<p style=\"text-indent:2em\">您的订单（编号为："+ ocode +")已收到，我们将尽快为您发货，请随时查看订单状态。感谢您的购买。</p>";
                break;
            case "2":     //已发货
                title ="您的商品已发出";
                content += "<p style=\"text-indent:2em\">您购买的（订单编号为："+ ocode +")的商品已发货，请留意快递状态，及时收货。</p>";
                break;
            case "3":
                title ="感谢您的购买";
                content += "<p style=\"text-indent:2em\">感谢您的购买，期待您的下次惠顾。</p>";
        }

        EmailUtil.build(emailSet.getEmServer(), emailSet.getEmAddress(), emailSet.getEmPassCode())
                .sendEmail(user.getUemail(), title, content);
        // System.out.println("邮件发送成功");
        // response.sendRedirect(request.getContextPath() + "/admin/order");
        // 使用iframe提交
        // 设置字符编码
        response.setContentType("text/html; charset=utf-8");
        // 获取响应流
        PrintWriter writer = response.getWriter();
        // 通过 top 访问顶层页面
        writer.println("<script>top.alert('邮件发送成功！')</script>");
        writer.flush();
    }
}
