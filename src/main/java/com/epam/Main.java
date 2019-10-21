package com.epam;

import com.epam.config.ApplicationConfig;
import com.epam.controller.UserController;
import com.epam.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

        ctx.register(ApplicationConfig.class);
        ctx.refresh();



    }
}
