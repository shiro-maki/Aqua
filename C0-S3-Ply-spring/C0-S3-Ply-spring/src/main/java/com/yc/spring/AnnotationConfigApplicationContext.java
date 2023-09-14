package com.yc.spring;

import com.yc.cinema.IocConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
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
    public AnnotationConfigApplicationContext(Class configClass) throws InstantiationException, IllegalAccessException, InvocationTargetException, UnsupportedEncodingException, ClassNotFoundException {
        this.configClass = configClass;
        final Annotation configuration=configClass.getAnnotation(Configuration.class);
        if(configuration==null){
            throw new RuntimeException("不是Spring配置类"+configClass);
        }
        this.config=configClass.newInstance();
        createBeanByBeanMethod();
        List<File> componentFiles=scanComponent();
//        componentFile.forEach(System.out::println);
        //根据字节码文件创建对象
        createBeanByClassFile(componentFiles);
    }

    private void createBeanByClassFile(List<File> componentFiles) throws UnsupportedEncodingException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        final URL url=getClass().getClassLoader().getResource("");
        String filepath = url.toString().substring("file:/".length());
        filepath=URLDecoder.decode(filepath, "utf-8");
        File rootFile=new File(filepath);
        //变量字节码文件
        for (File componentFile : componentFiles) {
            String classpath = componentFile.getAbsolutePath().substring(rootFile.getAbsolutePath().length());
            classpath=classpath.replace(".class", "");
            classpath=classpath.replaceAll("\\\\", ".");
            classpath=classpath.substring(1);
            //类路径创建类对象
            final Class<?> aClass=Class.forName(classpath);
            System.out.println("aClass = " + aClass);
            if(aClass.getAnnotation(Service.class)!=null||
            aClass.getAnnotation(Repository.class)!=null||
            aClass.getAnnotation(Controller.class)!=null||
            aClass.getAnnotation(Component.class)!=null){
                final Object bean = aClass.newInstance();
                final Service service = aClass.getAnnotation(Service.class);
                final Component component = aClass.getAnnotation(Component.class);
                if(service!=null){
                    if(service.value().length()>0){
                        IOC.put(service.value(),bean);
                    }
                } else if (component.value().length()>0) {
                    IOC.put(component.value(),bean);
                }
                else {
                    final String simpleName=aClass.getSimpleName();
                    String name=simpleName.substring(0,1).toLowerCase()+simpleName.substring(1);
                    IOC.put(name,bean);
                }
            }
        }
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

    public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException, UnsupportedEncodingException, ClassNotFoundException {
        AnnotationConfigApplicationContext cxt=new AnnotationConfigApplicationContext(IocConfig.class);
        cxt.IOC.forEach((name,bean)->{
            System.out.printf("%s : %s \n",name,bean);
        });
    }

    public Object getBean(String name){
        final Object bean = IOC.get(name);
        if(bean==null){
            throw new RuntimeException("没有这个bean");
        }
        else return bean;
    }
//    public <T> T getBean(Class<T> cls){
//
//    }
    private BeanInfo buildBeanInfo(Class beanClass) throws InstantiationException, IllegalAccessException {
       boolean isPrimary= beanClass.getAnnotation(Primary.class)!=null;
       BeanInfo beanInfo=new BeanInfo();
       beanInfo.isPrimmary=isPrimary;
       beanInfo.bean=beanClass.newInstance();
       //TODO扩展其他属性
        return beanInfo;

    }
}
class BeanInfo{
    Object bean;
    String scope="singleton";
    boolean isLazy;
    boolean isPrimmary;
    Class BeanClass;
}
