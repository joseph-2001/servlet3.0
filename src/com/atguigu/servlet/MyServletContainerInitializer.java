package com.atguigu.servlet;

import java.util.EnumSet;
import java.util.Set;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;

import com.atguigu.service.HelloService;

//容器启动的时候会将@HandlesTypes指定的这个类型下面的子类（实现类、子接口）传递过来
//传入感兴趣的类型，但是不包括指定的这个类型
@HandlesTypes(value = { HelloService.class })
public class MyServletContainerInitializer implements ServletContainerInitializer {

    /**
     * 在运行启动的时候，会运行onStartup方法
     * 
     * Set<Class<?>> arg0：感兴趣的类型的所有子类型
     * ServletContext arg1：代表当前web应用的ServletContext，一个Web应用一个ServletContext
     * 
     * */
    @Override
    public void onStartup(Set<Class<?>> classes, ServletContext context) throws ServletException {
        // TODO Auto-generated method stub
        System.out.println("感兴趣的类型：");
        for (Class<?> clazz : classes) {
            System.out.println(clazz);
        }
        
        //注册组件ServletRegistration
        javax.servlet.ServletRegistration.Dynamic servlet = context.addServlet("userServlet", new UserServlet());
        //配置Servlet的映射信息
        servlet.addMapping("/user");

        //注册Listener
        context.addListener(UserListener.class);

        //注册FilterFilterRegistration
        javax.servlet.FilterRegistration.Dynamic filter = context.addFilter("userFilter", UserFilter.class);
        //配置Filter的映射信息
        filter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
    }
}
