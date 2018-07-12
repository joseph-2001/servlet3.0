package com.atguigu.servlet;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/async", asyncSupported = true)
public class HelloServletAsync extends HttpServlet {

    private static final long serialVersionUID = -5315848161732585355L;

    public void sayHello() throws Exception {
        System.out.println(Thread.currentThread() + " running...");
        Thread.sleep(3000);
    }

    @Override
    protected void doGet(final HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //开启异步处理asyncSupported = true
        //开启异步模式
        System.out.println("主线程开始..." + Thread.currentThread());
        final AsyncContext startContext = req.startAsync();
        //业务逻辑进行异步处理，开始异步处理
        startContext.start(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("副线程开始..." + Thread.currentThread());
                    sayHello();
                    startContext.complete();
                    //获取到异步的上下文的响应
                    ServletResponse response = startContext.getResponse();
                    response.getWriter().write("hello async...");
                    System.out.println("副线程结束..." + Thread.currentThread());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println("主线程结束..." + Thread.currentThread());
    }

}
