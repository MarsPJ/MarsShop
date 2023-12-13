package com.marsshop.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//要加上注解，否则这个类不会被解析
@WebListener
public class ApplicationListener implements ServletContextListener {
    //当 Web 应用程序启动时，容器会调用此方法。
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext app = sce.getServletContext(); // 获取application对象
        // 获取项目根路径
        String root = app.getContextPath();
        app.setAttribute("ctx", root);
    }
    //当 Web 应用程序关闭时，容器会调用此方法。
    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
