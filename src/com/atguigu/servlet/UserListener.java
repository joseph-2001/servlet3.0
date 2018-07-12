package com.atguigu.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 监听项目的启动和销毁
 * */
public class UserListener implements ServletContextListener {

    //监听ServletContextEvent销毁
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        System.out.println("UserListener...contextDestroyed...");
    }

    //监听ServletContextEvent启动初始化
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        System.out.println("UserListener...contextInitialized...");
    }
}
