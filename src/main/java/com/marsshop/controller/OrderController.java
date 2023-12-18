package com.marsshop.controller;

import com.marsshop.domain.Order;
import com.marsshop.domain.OrderSelectParam;
import com.marsshop.domain.Page;
import com.marsshop.service.Impl.OrderServiceImpl;
import com.marsshop.service.OrderService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(name = "OrderController", value = "/admin/order")
public class OrderController extends HttpServlet {
    OrderService orderService = new OrderServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String msg = (String) session.getAttribute("msg");
        if (msg != null && !msg.isEmpty()) {
            session.removeAttribute("msg");
            request.setAttribute("msg", msg);
        }

        String ocode = request.getParameter("ocode");
        String uname = request.getParameter("uname");
        String _otimeBegin = request.getParameter("otimeBegin");
        String _otimeEnd = request.getParameter("otimeEnd");
        String _ostatus = request.getParameter("ostatus");

        // 初始化条件对象
        OrderSelectParam param = new OrderSelectParam();
        param.setOcode(ocode);
        param.setUname(uname);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            if (_otimeBegin!= null && !_otimeBegin.isEmpty()) {
                param.setOtimeBegin(simpleDateFormat.parse(_otimeBegin));
            }
            if (_otimeEnd!= null && !_otimeEnd.isEmpty()) {
                param.setOtimeEnd(simpleDateFormat.parse(_otimeEnd));
            }
            if (_ostatus!= null && !_ostatus.isEmpty()) {
                param.setOstatus(Integer.parseInt(_ostatus));
            }

        } catch (ParseException e) {
            e.printStackTrace();
            request.setAttribute("msg", "查询日期格式不正确！");
            request.getRequestDispatcher("/admin/order.jsp").forward(request, response);
        }

        String _pageIndex = request.getParameter("pageIndex");

        int pageIndex =1;
        if (_pageIndex != null && !_pageIndex.isEmpty()) {
            pageIndex = Integer.parseInt(_pageIndex);
        }
        Page page = new Page(pageIndex);
        // 使用条件对象进行分页查询
        List<Order> orderList = orderService.selectByParam(param, page);
        System.out.println("size: " + orderList.size());
        request.setAttribute("orderList", orderList);
        request.setAttribute("page", page);
        request.getRequestDispatcher("/admin/order.jsp").forward(request, response);
    }

}
