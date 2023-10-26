package com.eduard.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AopExpressions {


    @Pointcut("execution(* com.eduard.aopdemo.dao.*.*(..))")
    public void forDaoPackage() {}

    //create pointcut for setter methods
    @Pointcut("execution(* com.eduard.aopdemo.dao.*.set*(..))")
    public void forSetterMethods() {}

    //create pointcut for getter methods
    @Pointcut("execution(* com.eduard.aopdemo.dao.*.get*(..))")
    public void forGetterMethods() {}

    //create pointcut: include package ... exclude getter/setter
    @Pointcut("forDaoPackage() && !(forSetterMethods() || forGetterMethods())")
    public void forDaoPackageNoGetterNoSetter() {}

}
