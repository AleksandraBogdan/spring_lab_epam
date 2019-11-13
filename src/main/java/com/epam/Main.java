package com.epam;

import com.epam.config.ApplicationConfig;
import com.epam.controller.TaskController;
import com.epam.controller.UserController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(ApplicationConfig.class);
        ctx.refresh();
        ClassPathXmlApplicationContext xmlContext = new ClassPathXmlApplicationContext("config.xml");
        RolesService rolesService = xmlContext.getBean(RolesService.class);

        UserController userController = ctx.getBean(UserController.class);
        TaskController taskController = ctx.getBean(TaskController.class);
    }
}
