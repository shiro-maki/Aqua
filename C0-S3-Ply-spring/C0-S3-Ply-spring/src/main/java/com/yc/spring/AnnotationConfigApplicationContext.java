package com.yc.spring;

import com.yc.cinema.IocConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;

public class AnnotationConfigApplicationContext {
    private final Class configClass;
    private final Object config;
    private Map<String,Object> IOC=new HashMap<>();
    public AnnotationConfigApplicationContext(Class configClass) throws InstantiationException, IllegalAccessException, InvocationTargetException, UnsupportedEncodingException {
        this.configClass = configClass;
        final Annotation configuration=configClass.getAnnotation(Configuration.class);
        if(configuration==null){
            throw new RuntimeException("不是Spring配置类"+configClass);
        }
        this.config=configClass.newInstance();
        createBeanByBeanMethod();
        List<File> componentFile=scanComponent();
        componentFile.forEach(System.out::println);
    }

    private List<File> scanComponent() throws UnsupportedEncodingException {
        List<File> ret=new ArrayList<>();
        final ComponentScan componentScan = (ComponentScan) configClass.getAnnotation(ComponentScan.class);
        if(componentScan==null){
            return Collections.EMPTY_LIST;
        }else {
            final String[] packages = componentScan.value();
            final ClassLoader classLoader = getClass().getClassLoader();
            for(String aPackage:packages){
                final String path=aPackage.replaceAll("\\.", "/");
                System.out.println("path = " + path);
                final URL url = classLoader.getResource(path);
                System.out.println("url = " + url);
                final String filepath = url.toString().substring("file:/".length());
                scanComponent(filepath, ret);
            }
        }
        return  ret;
    }
    private void scanComponent(String filepath,List<File> list) throws UnsupportedEncodingException {
        filepath= URLDecoder.decode(filepath, "utf-8");
        File dir=new File(filepath);
        for (File file : dir.listFiles()) {
            if(file.isFile()&&file.getName().endsWith(".class")){
                list.add(file);
            } else if (file.isDirectory()) {
                scanComponent(file.getAbsolutePath(),list);
            }else {

            }
        }
    }

    private void createBeanByBeanMethod() throws InvocationTargetException, IllegalAccessException {
        final Method[] declaredMethods = configClass.getDeclaredMethods();
        for (Method declaredMethod:declaredMethods){
            final Bean beanAnnotation = declaredMethod.getAnnotation(Bean.class);
            if(beanAnnotation==null){
                //没有加载bean注解
            }else{
                declaredMethod.setAccessible(true);//强制访问
                final Object bean = declaredMethod.invoke(config);//创建bean
                String[] names;
                if(beanAnnotation.value().length==0){
                    names = new String[]{declaredMethod.getName()};
                    // 获取bean 的name
                }else {
                    names= beanAnnotation.value();
                }
                for(String name:names){
                    IOC.put(name,bean);
                }
            }
        }
    }

    public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException, UnsupportedEncodingException {
        AnnotationConfigApplicationContext cxt=new AnnotationConfigApplicationContext(IocConfig.class);
        cxt.IOC.forEach((name,bean)->{
            System.out.printf("%s : %s \n",name,bean);
        });
    }
}
