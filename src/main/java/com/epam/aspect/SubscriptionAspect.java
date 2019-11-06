package com.epam.aspect;


import com.epam.controller.TaskController;
import com.epam.exception.SubscriptionException;
import com.epam.model.Task;
import com.epam.model.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;


@Aspect
@Component
public class SubscriptionAspect {
    private TaskController taskController;

    @Autowired
    SubscriptionAspect(TaskController taskController) {
        this.taskController = taskController;
    }

    @Before("execution(public * com.epam.controller.TaskController.createTask()) && args(user, task)")
    public void checkSubscriptionAdvice(User user, Task task) {
        System.out.println("Aspect");
        if (taskController.getAllTasks().size() == 10 && !user.getSubscription().equals(getSecretWord())) {
            throw new SubscriptionException();
        }
    }

    private String getSecretWord() {
        String secretWord = "secret";
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(secretWord.getBytes());
        return Arrays.toString(md.digest());
    }
}
