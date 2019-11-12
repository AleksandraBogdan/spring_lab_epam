package com.epam;

import com.epam.aspect.SubscriptionAspect;
import com.epam.config.ApplicationConfig;
import com.epam.controller.TaskController;
import com.epam.controller.UserController;
import com.epam.model.User;
import javafx.application.Application;
import org.springframework.aop.config.AspectComponentDefinition;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(ApplicationConfig.class);
        ctx.refresh();
        ClassPathXmlApplicationContext xmlContext = new ClassPathXmlApplicationContext("config.xml");
        RolesService rolesService = xmlContext.getBean(RolesService.class);

        UserController userController = ctx.getBean(UserController.class);
        TaskController taskController = ctx.getBean(TaskController.class);


        User testUser = User.builder().name("Sanya").surname("Smith").email("sanya@mail.com")
                .password("1234").build();

     

        //System.out.println(userController.showAll());
        userController.signUp(testUser);
        userController.signIn((long)testUser.getId(), testUser);
        //userController.signUp(testUser);


        System.out.println(userController.showAll());


        /*Task testTask = Task.builder().id(3).name("lunch").isDone(false).build();
        taskContoller.createTask(userController.signInUser, testTask);
        System.out.println(taskContoller.getAllTasks());
        taskContoller.setDone(testTask);
        System.out.println(taskContoller.getAllTasks());
        taskContoller.setUndone(testTask);
        System.out.println(taskContoller.getAllTasks());
        taskContoller.setPriority(testTask, TaskPriority.MEDIUM);
        System.out.println(taskContoller.getAllTasks());
        taskContoller.deleteTask(testTask);
        System.out.println(taskContoller.getAllTasks());*/

        /*Task testTask1 = Task.builder().id(3).name("lunch").isDone(false).build();
        taskContoller.createTask(userController.signInUser, testTask1);
        Task testTask2 = Task.builder().id(3).name("lunch").isDone(false).build();
        taskContoller.createTask(userController.signInUser, testTask2);
        Task testTask3 = Task.builder().id(3).name("lunch").isDone(false).build();
        taskContoller.createTask(userController.signInUser, testTask3);
        Task testTask4 = Task.builder().id(3).name("lunch").isDone(false).build();
        taskContoller.createTask(userController.signInUser, testTask4);
        Task testTask5 = Task.builder().id(3).name("lunch").isDone(false).build();
        taskContoller.createTask(userController.signInUser, testTask5);
        Task testTask6 = Task.builder().id(3).name("lunch").isDone(false).build();
        taskContoller.createTask(userController.signInUser, testTask6);
        Task testTask7 = Task.builder().id(3).name("lunch").isDone(false).build();
        taskContoller.createTask(userController.signInUser, testTask7);
        Task testTask8 = Task.builder().id(3).name("lunch").isDone(false).build();
        taskContoller.createTask(userController.signInUser, testTask8);
        Task testTask9 = Task.builder().id(3).name("lunch").isDone(false).build();
        taskContoller.createTask(userController.signInUser, testTask9);
        System.out.println(taskContoller.getAllTasks());*/

        /*userController.subscribe(userController.signInUser);
        System.out.println(userController.signInUser);*/


        SubscriptionAspect aspect = ctx.getBean(SubscriptionAspect.class);

        for (int i = 3; i < 20; i++) {
            Task testTask = Task.builder().id(i).name("lunch").isDone(false).userId(3).build();
            aspect.checkSubscriptionAdvice(testUser, testTask);
            taskContoller.createTask(testUser, testTask);
        }
        System.out.println("DONE");

    }
}
