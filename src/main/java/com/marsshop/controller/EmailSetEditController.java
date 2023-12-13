package com.marsshop.controller;

import com.marsshop.domain.EmailSet;
import com.marsshop.service.EmailSetService;
import com.marsshop.service.Impl.EmailSetServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.awt.*;
import java.io.IOException;

@WebServlet(name = "EmailSetEditController", value = "/admin/emailset/edit")
public class EmailSetEditController extends HttpServlet {
    EmailSetService emailSetService = new EmailSetServiceImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String emId = request.getParameter("emId");
        String emServer = request.getParameter("emServer");
        String emAddress = request.getParameter("emAddress");
        String emPassCode = request.getParameter("emPassCode");

        EmailSet emailSet = new EmailSet();
        if (emId != null && !emId.isEmpty()) {
            emailSet.setEmId(Integer.parseInt(emId));
        }
        emailSet.setEmServer(emServer);
        emailSet.setEmAddress(emAddress);
        emailSet.setEmPassCode(emPassCode);

        emailSetService.saveOrUpdate(emailSet);

        HttpSession session = request.getSession();
        session.setAttribute("msg", "成功设置邮箱信息！");
        response.sendRedirect(request.getContextPath() + "/admin/emailset");
    }
}
