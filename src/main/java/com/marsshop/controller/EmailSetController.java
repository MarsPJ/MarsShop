package com.marsshop.controller;

import com.marsshop.domain.EmailSet;
import com.marsshop.service.EmailSetService;
import com.marsshop.service.Impl.EmailSetServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EmailSetController", value = "/admin/emailset")
public class EmailSetController extends HttpServlet {
    EmailSetService emailSetService = new EmailSetServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String msg = (String) session.getAttribute("msg");
        if (msg != null && !msg.isEmpty()) {
            session.removeAttribute("msg");
            request.setAttribute("msg", msg);
        }

        EmailSet emailSet = emailSetService.select();
        request.setAttribute("email", emailSet);
        request.getRequestDispatcher("/admin/emailset.jsp").forward(request, response);
    }

}
