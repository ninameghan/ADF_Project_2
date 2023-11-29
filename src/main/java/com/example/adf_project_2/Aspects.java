package com.example.adf_project_2;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
@Slf4j
public class Aspects {

    // Logging point for single method
    @Pointcut(value = "execution(* com.example.adf_project_2.repositories.IUserRepository.save(..))")
    public void saveInUserRepo(){}
    @Before("saveInUserRepo()")
    public void logBeforeSaveUserRepo(JoinPoint joinPoint){
        log.info("***** I am before " + joinPoint.getSignature().toShortString() + " with arguments "
                + Arrays.toString(joinPoint.getArgs()) + " *****");
    }

    @After("saveInUserRepo()")
    public void logAfterSaveUserRepo(JoinPoint joinPoint) {
        log.info("***** I am after " + joinPoint.getSignature().toShortString() + " with arguments "
                + Arrays.toString(joinPoint.getArgs()) + " *****");
    }

    // Logging point for all methods in a class
    @Pointcut(value = "execution(* com.example.adf_project_2.repositories.IPropertyRepository.*(..))")
    public void allMethodsInTenantRepo(){}
    @Before("allMethodsInTenantRepo()")
    public void logBeforeTenantRepoMethods(JoinPoint joinPoint){
        log.info("***** I am before " + joinPoint.getSignature().toShortString() + " with arguments "
                + Arrays.toString(joinPoint.getArgs()) + " *****");
    }

    @After("allMethodsInTenantRepo()")
    public void logAfterTenantRepoMethods(JoinPoint joinPoint) {
        log.info("***** I am after " + joinPoint.getSignature().toShortString() + " with arguments "
                + Arrays.toString(joinPoint.getArgs()) + " *****");
    }

    //Logging point for all methods in all classed annotated @Controller

    @Pointcut(value = "within(@org.springframework.stereotype.Controller *)")
    public void allMethodsInClassesAnnotatedWithController(){}
    @Before("allMethodsInClassesAnnotatedWithController()")
    public void logBeforeController(JoinPoint joinPoint){
        log.info("***** I am before " + joinPoint.getSignature().toShortString() + " with arguments "
                + Arrays.toString(joinPoint.getArgs()) + " *****");
    }

    @After("allMethodsInClassesAnnotatedWithController()")
    public void logAfterController(JoinPoint joinPoint) {
        log.info("***** I am after " + joinPoint.getSignature().toShortString() + " with arguments "
                + Arrays.toString(joinPoint.getArgs()) + " *****");
    }
}
