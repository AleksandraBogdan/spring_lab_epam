package com.epam;

import com.epam.config.ApplicationConfig;
import com.epam.controller.TaskContoller;
import com.epam.controller.UserController;
import com.epam.model.Task;
import com.epam.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

        ctx.register(ApplicationConfig.class);
        ctx.refresh();

        UserController userController = ctx.getBean(UserController.class);
        TaskContoller taskContoller = ctx.getBean(TaskContoller.class);

        User testUser = User.builder().id(2).name("Sanya").surname("Smith").email("alex@mail.com")
                .password("1234").build();

        userController.signUp(testUser);
        userController.signIn(testUser);
        System.out.println(userController.signInUser);
        Task testTask = Task.builder().id(3).name("lunch").isDone(false).userId(1).build();
        taskContoller.createTask(testTask);

        System.out.println(taskContoller.getAllTasks());

        taskContoller.setDone(testTask);
        System.out.println("Task done? " + testTask.isDone());
        taskContoller.setUndone(testTask);
        System.out.println("Task done? " + testTask.isDone());

        taskContoller.deleteTask(testTask);

    }
}
