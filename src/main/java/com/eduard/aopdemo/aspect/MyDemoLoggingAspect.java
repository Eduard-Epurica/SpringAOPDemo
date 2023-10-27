package com.eduard.aopdemo.aspect;

import com.eduard.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.SortedMap;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    // this is where we add all of our related advices for logging
    // let's start with a @Before advice
    // @Before("execution(public void add*())")
    //Star is for wildcard patterns (will match anything that starts with add
    @Before("com.eduard.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterNoSetter()")
    public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {

        System.out.println("\n============> Executing custom AOP @Before code on methods add*()");

        // display the method signature
        MethodSignature methodSignature = (MethodSignature) theJoinPoint.getSignature();
        System.out.println("Method: " + methodSignature);


        // display method arguments

        //get args
        Object[] args = theJoinPoint.getArgs();

        //loop through args
        for (Object tempArg: args){
            System.out.println(tempArg);

            if( tempArg instanceof Account) {
                //downcast and print Account specific info
                Account theAccount = (Account) tempArg;
                System.out.println("account name: " + theAccount.getName());
                System.out.println("account level: " + theAccount.getLevel());
            }
        }

    }

    //add a new advice for @AfterReturing on the findAccounts method
    @AfterReturning(pointcut = "execution(* com.eduard.aopdemo.dao.AccountDAO.findAccounts(..))", returning = "results")
    public void afterReturningFindAccountAdvice(JoinPoint joinPoint, List<Account> results){

        //print out which method we are  advising on
        String method = joinPoint.getSignature().toLongString();
        System.out.println("\n============> Executing @After Returning on method " + method);

        System.out.println(results);

        //pre process the data
        convertToUpperCase(results);
    }

    private void convertToUpperCase(List<Account> results) {

        for(Account d:results){
            d.setName(d.getName().toUpperCase());
        }
    }


    @AfterThrowing(
            pointcut = "execution(* com.eduard.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "theException"
    )
    public void afterThrowingFindAccountsAdvice(
            JoinPoint joinPoint, Throwable theException){

        //print out which method we are advising on
        String method = joinPoint.getSignature().toLongString();
        System.out.println("\n============> Executing @AfterThrowing on method " + method);

        //log the exception
        System.out.println("\n============> Exception is " + theException);

    }

    @After("execution(* com.eduard.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(
            JoinPoint joinPoint){


        //print out which method we are advising on
        String method = joinPoint.getSignature().toLongString();
        System.out.println("\n============> Executing @AfterFinally on method " + method);
    }


    @Around("execution(* com.eduard.aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortune(
            ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        // print out method we are advising on
        String method = proceedingJoinPoint.getSignature().toString();
        System.out.println("\n===========> Executing @Around on method: " + method);

        // get begin timestamp
        long begin = System.currentTimeMillis();

        // execute the method
        Object result = null;
        try{
            result = proceedingJoinPoint.proceed();
        } catch (Exception e) {
            //log the exception
            System.out.println(e.getMessage());

            //give user a custom message
            //result = "Major accident! Helicopter on the way";

            //rethrow exception
            throw e;
        }

        // get end timestamp
        long end = System.currentTimeMillis();

        // compute and display duration
        long duration = end - begin;
        System.out.println("\n========> Duration: " + duration/1000 + " seconds");

        return result;
    }


}
