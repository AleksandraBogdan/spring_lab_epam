package com.epam.aspect;


import com.epam.exception.SubscriptionException;
import com.epam.model.Task;
import com.epam.model.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class SubscriptionAspect {

    @Before("execution(* com.epam.controller.TaskController.createTask() && args(user,task)")
    public void checkSubscriptionAdvice(JoinPoint joinPoint, User user, Task task) {
        if (user.getUserTasks().size() == 10 && user.getSubscription() != "secret") {
            throw new SubscriptionException();
        }
    }
}
