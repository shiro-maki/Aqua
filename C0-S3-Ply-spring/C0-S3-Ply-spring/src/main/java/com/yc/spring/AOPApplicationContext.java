package com.yc.spring;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Bean;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AOPApplicationContext extends AnnotationConfigApplicationContext{
    public AOPApplicationContext(Class configClass) throws Exception {
        super(configClass);
        Map<String,AspectInfo> aspectInfoMap=new HashMap<>();
        for (Map.Entry<String, BeanInfo> entry : IOC.entrySet()) {
            final String name=entry.getKey();
            final BeanInfo beanInfo= entry.getValue();
            final Aspect aspect=beanInfo.bean.getClass().getAnnotation(Aspect.class);
            if(aspect==null){
                continue;
            }


            final Method[] methods=beanInfo.bean.getClass().getDeclaredMethods();
            for (Method method : methods) {
                String pointcutExp=null;
                final Pointcut pointcut=method.getAnnotation(Pointcut.class);
                final Before before=method.getAnnotation(Before.class);
                if(pointcut!=null){
                    pointcutExp=pointcut.value();
                }else if(before!=null){
                    pointcutExp=before.value();
                }else {
                    continue;
                }
                //判断切点表达式是否引用了方法名切点
                if(pointcutExp.matches("\\w+\\(\\)")){
                    final String pointcutMethodName = pointcutExp.replaceAll("(\\w+)\\(\\)", "$1");
                    final Method pointcutMethod = beanInfo.bean.getClass().getDeclaredMethod(pointcutMethodName);
                    pointcutExp=pointcutMethod.getAnnotation(Pointcut.class).value();
                }
                AspectInfo aspectInfo=aspectInfoMap.get(pointcutExp);
                if(aspectInfo==null){
                    aspectInfo=new AspectInfo();
                    aspectInfo.pointcutExp=pointcutExp;
                    aspectInfoMap.put(pointcutExp, aspectInfo);
                }

                if(before!=null){
                    aspectInfo.befores.add(method);
                }
            }
        }
        aspectInfoMap.values().forEach(System.out::println);
    }
    class AspectInfo{
        String pointcutExp;
        //5个增强方法合集
        List<Method> befores=new ArrayList<>();
        List<Method> afters=new ArrayList<>();
        List<Method> afterThrowings=new ArrayList<>();
        List<Method> afterReturnings=new ArrayList<>();
        List<Method> arounds=new ArrayList<>();

        @Override
        public String toString() {
            return "AspectInfo{" +
                    "pointcutExp='" + pointcutExp + '\'' +
                    ", befores=" + befores +
                    ", afters=" + afters +
                    ", afterThrowings=" + afterThrowings +
                    ", afterReturnings=" + afterReturnings +
                    ", arounds=" + arounds +
                    '}';
        }
    }

}
