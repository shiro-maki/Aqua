package com.yc.spring.aop;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

//动态字节码技术，基于继承是西安
public class CGLibProxy {
    public static void main(String[] args) {
        Person p=new 被告();
        final Person proxy=proxy(p);
        proxy.say();
    }
    public static <T> T proxy(T t) {
        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(t.getClass());
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("被告不在场!");
                final Object ret = methodProxy.invoke(t, objects);
                System.out.println("被告是精神病");
                return ret;
            }
        });
        return (T) enhancer.create();
    }
}
