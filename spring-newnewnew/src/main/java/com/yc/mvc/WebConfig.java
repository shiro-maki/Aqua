package com.yc.mvc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebConfig implements WebApplicationInitializer {

    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext cxt=new AnnotationConfigWebApplicationContext();
       cxt.register(MvcConfig.class);
        DispatcherServlet servlet=new DispatcherServlet((WebApplicationContext) cxt);
        ServletRegistration.Dynamic registration= servletContext.addServlet("app", servlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
    }
}
