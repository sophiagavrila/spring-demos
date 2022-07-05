package com.revature.quizzard.web.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
//@Component
public class LoggingAspect {

    @Pointcut("within(com.revature.quizzard..*)")
    public void logAll() {}

    @Before("logAll()")
    public void logMethodStart(JoinPoint jp) {
        String methodSig = extractMethodSignature(jp);
        String argStr = Arrays.toString(jp.getArgs());
        System.out.println(methodSig + " invoked at " + LocalDateTime.now());
        System.out.println("Input arguments: " + argStr);
    }

    @AfterReturning(pointcut="logAll()", returning="returned")
    public void logMethodReturn(JoinPoint jp, Object returned) {
        String methodSig = extractMethodSignature(jp);
        System.out.println(methodSig + " successfully returned at " + LocalDateTime.now());
        System.out.println("Object returned: " + returned);
    }

    @AfterThrowing(pointcut="logAll()", throwing="e")
    public void errorOccurence(JoinPoint jp, Exception e) {
        String methodSig = extractMethodSignature(jp);
        System.out.println(e + " was thrown in method: " + methodSig + " at " + LocalDateTime.now());
    }

    private String extractMethodSignature(JoinPoint jp) {
        return jp.getTarget().getClass().toString() + "." + jp.getSignature().getName();
    }

}
