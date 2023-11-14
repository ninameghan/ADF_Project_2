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

    // Logging point for all methods in a class
    @Pointcut(value = "execution(* com.example.adf_project_2.repositories.ITenantRepository.*(..))")
    public void allMethodsInTenantRepo(){}
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

    //TODO: Logging point for all methods in all classed annotated with a particular annotation (eg. all @Controller classes)


}
