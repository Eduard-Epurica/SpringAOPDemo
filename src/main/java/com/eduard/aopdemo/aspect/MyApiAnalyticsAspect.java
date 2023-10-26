package com.eduard.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(3)
public class MyApiAnalyticsAspect
{

    @Before("com.eduard.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterNoSetter()")
    public void performApiAnalytics() {

        System.out.println("\n============> Performing API analytics");

    }



}
