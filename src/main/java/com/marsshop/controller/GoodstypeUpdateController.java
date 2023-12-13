package com.marsshop.controller;

import com.marsshop.domain.Goodstype;
import com.marsshop.service.GoodstypeService;
import com.marsshop.service.Impl.GoodstypeServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * 更新商品类别信息
 */
@WebServlet(name = "GoodstypeUpdateController", value = "/admin/goodstype/update")
public class GoodstypeUpdateController extends HttpServlet {
    // 业务层对象
   private GoodstypeService goodstypeService = new GoodstypeServiceImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tid = request.getParameter("tid");
        String tname =request.getParameter("tname");
        Goodstype goodstype = new Goodstype();
        goodstype.setTid(Integer.parseInt(tid));
        goodstype.setTname(tname);
        int result = goodstypeService.update(goodstype);
        if (result == -1) {
            request.setAttribute("msg", "此商品类别名称已存在");
            // 信息回显的时候使用
            request.setAttribute("type", goodstype);
            request.getRequestDispatcher("/admin/goodstype-edit.jsp").forward(request, response);
        } else if (result == 1) {
            HttpSession session = request.getSession();
            session.setAttribute("msg", "商品类别信息修改成功！");
            response.sendRedirect(request.getContextPath() + "/admin/goodstype");
        }
    }
}
