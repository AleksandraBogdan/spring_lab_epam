package com.epam.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;


public class WebApplicationConfig implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext)  {
        AnnotationConfigWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext();
        //webApplicationContext.scan("com.epam.config");
        webApplicationContext.register(ApplicationConfig.class);
        webApplicationContext.register(SwaggerConfig.class);

       // servletContext.addListener(new ContextLoaderListener(webApplicationContext));
        webApplicationContext.setServletContext(servletContext);
        //webApplicationContext.refresh();
        DispatcherServlet dispatcherServlet = new DispatcherServlet(webApplicationContext);
        ServletRegistration.Dynamic appServlet = servletContext.addServlet("mvc", dispatcherServlet);

        appServlet.setLoadOnStartup(1);
        appServlet.addMapping("/");
        System.out.println("Good");

    }



}
