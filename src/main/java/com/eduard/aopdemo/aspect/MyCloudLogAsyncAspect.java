package com.eduard.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class MyCloudLogAsyncAspect
{

    @Before("com.eduard.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterNoSetter()")
    public void logToCloudAsync() {

        System.out.println("\n============> Logging to Cloud in async fashion");

    }




}