package com.epam;

import com.epam.aspect.SubscriptionAspect;
import com.epam.config.ApplicationConfig;
import com.epam.controller.TaskController;
import com.epam.controller.UserController;
import com.epam.model.Task;
import com.epam.model.User;
import javafx.application.Application;
import org.springframework.aop.config.AspectComponentDefinition;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(ApplicationConfig.class);
        ctx.refresh();

        UserController userController = ctx.getBean(UserController.class);
        TaskController taskContoller = ctx.getBean(TaskController.class);

        User testUser = User.builder().id(2).name("Sanya").surname("Smith").email("alex@mail.com")
                .password("1234").userTasks(new ArrayList<>()).subscription("wrong").build();


        userController.signUp(testUser);
        userController.signIn(testUser);
        System.out.println(userController.signInUser);
        //userController.signUp(testUser);
        SubscriptionAspect aspect = ctx.getBean(SubscriptionAspect.class);

        for (int i = 3; i < 20; i++) {
            Task testTask = Task.builder().id(i).name("lunch").isDone(false).userId(3).build();
            aspect.checkSubscriptionAdvice(testUser, testTask);
            taskContoller.createTask(testUser, testTask);
        }
        System.out.println("DONE");
    }
}
