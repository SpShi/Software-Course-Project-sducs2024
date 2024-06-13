package com.fate.elite.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

;

@WebListener
public class TypeServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

//        //1.获取当前数据库中所有的类型信息
//        TypeBiz biz = new TypeBiz();
//        List<Type> types = biz.getAll();
//        ChamberBiz biz2=new ChamberBiz();
//        List<Chamber> chambers=biz2.getAll();
//        //2.获取application对象
//        ServletContext application = servletContextEvent.getServletContext();
//
//        //3.将信息存在application对象中
//        application.setAttribute("types",types);
//        application.setAttribute("chambers",chambers);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
