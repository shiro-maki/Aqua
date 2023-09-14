package com.yc.spring.aop;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspect {
    //前置增强，日志输出
//    @Before("execution(* com.yc.spring..biz.*Biz.*(..))")
//    public void before(JoinPoint jp){
////        System.out.println("jp.getSignature() = " + jp.getSignature());
////        System.out.println("jp.getArgs() = " + jp.getArgs());
//    }
    @Pointcut("execution(* com.yc.spring..biz.*Biz.modify(..))")
    public void pointcut(){}
    //后置增强
    @After("pointcut()")
    public void after(JoinPoint jp){
        System.out.println("=========After======== " + jp.getSignature());
    }

    @Before("pointcut()")
    public void before1(JoinPoint jp){
        System.out.println("========Before========" + jp.getSignature());
    }
    //返回增强，会返回结果,ret为返回结果
    @AfterReturning(value="pointcut()",returning = "ret")
    public void returning(JoinPoint jp,Object ret){
        System.out.println("========AfterReturning========" + jp.getSignature()+":"+ret);
    }

    //异常增强,业务方法出现异常时执行的方法
    @AfterThrowing(value="pointcut()",throwing = "t")
    public void throwing(JoinPoint jp,Throwable t){
        System.out.println("========AfterThrowing========" + jp.getSignature()+":"+t);

    }

//    1.业务方法需要手动执行
//    2.此返回值就是业务方法的返回值
    @Around("pointcut()")
    public Object aroud(ProceedingJoinPoint pjp){
        Object ret=null;
        try {
            long time=System.currentTimeMillis();
            ret=pjp.proceed();
            time=System.currentTimeMillis()-time;
            System.out.println("===========AfterAround============");
            System.out.println("共耗时"+time+"毫秒");
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return ret;
    }

    @After("execution(* com.yc.spring.aop.*.test(..))")
    public void advice(){
        System.out.println("----------advice-------");
    }




}
