package com.yc.spring.aop;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
public class JdkProxy {
    public static void main(String[] args) {
        Person p=new 被告();
        final Person proxy=proxy(p);
        proxy.say();
    }
    public static <T> T proxy(T t) {
        return (T) Proxy.newProxyInstance(
                t.getClass().getClassLoader(),
                t.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("被告不在场!");
                        final Object ret = method.invoke(t, args);
                        System.out.println("被告是精神病");
                        return ret;
                    }
                }
        );
    }
}
