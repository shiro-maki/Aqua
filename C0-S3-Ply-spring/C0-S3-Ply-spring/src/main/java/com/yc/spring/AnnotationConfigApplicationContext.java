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
    protected Map<String, BeanInfo> IOC = new HashMap<>();


    public AnnotationConfigApplicationContext(Class configClass) throws Exception {
        this.configClass = configClass;
        final Annotation configuration = configClass.getAnnotation(Configuration.class);
        if (configuration == null) {
            throw new RuntimeException("不是Spring配置类" + configClass);
        }
        this.config = configClass.newInstance();
        // 构建 bean 对象
        createBeanByBeanMethod();

        // 包扫描 => 扫描组件
        List<File> compnentFiles = scanComponent();

        // 根据字节码文件创建对象
        createBeanByClassFile(compnentFiles);

    }

    private void createBeanByClassFile(List<File> compnentFiles) throws Exception {
        // 获取根目录
        final URL url = getClass().getClassLoader().getResource("");
        String filepath = url.toString().substring("file:/".length());
        filepath = URLDecoder.decode(filepath, "utf-8");
        File rootFile = new File(filepath);

        // 变量字节码文件
        for (File compnentFile : compnentFiles) {
            String classpath = compnentFile.getAbsolutePath().substring(rootFile.getAbsolutePath().length());
            classpath = classpath.replace(".class", "");
            classpath = classpath.replaceAll("\\\\", ".");
            // 去掉第一个 . 号
            classpath = classpath.substring(1);
            // 类路径 创建 类对象
            final Class<?> aClass = Class.forName(classpath);
            // 判断是否是组件
            if (aClass.getAnnotation(Service.class) != null ||
                    aClass.getAnnotation(Repository.class) != null ||
                    aClass.getAnnotation(Controller.class) != null ||
                    aClass.getAnnotation(Component.class) != null) {
                // 创建 bean
                // final Object bean = aClass.newInstance();
                BeanInfo bi = buildBeanInfo(aClass);
                // 获取 name
                final Service service = aClass.getAnnotation(Service.class);
                final Repository repository = aClass.getAnnotation(Repository.class);
                final Controller controller = aClass.getAnnotation(Controller.class);
                final Component component = aClass.getAnnotation(Component.class);
                if (service != null) {
                    if (service.value().length() > 0) {
                        IOC.put(service.value(), bi);
                    }
                } else if (component != null) {
                    if (component.value().length() > 0) {
                        IOC.put(component.value(), bi);
                    }
//                } else if ()... // TODO
                } else {
                    // 类名
                    final String simpleName = aClass.getSimpleName();
                    // 首字母小写
                    String name = simpleName.substring(0, 1).toLowerCase() + simpleName.substring(1);
                    IOC.put(name, bi);
                }
            }
        }
    }

    // 包扫描 => 扫描组件
    private List<File> scanComponent() throws UnsupportedEncodingException {
        List<File> ret = new ArrayList<>();
        final ComponentScan componentScan = (ComponentScan) configClass.getAnnotation(ComponentScan.class);
        if (componentScan == null) {
            return Collections.EMPTY_LIST;
        } else {
            final String[] packages = componentScan.value();
            final ClassLoader classLoader = getClass().getClassLoader();
            for (String aPackage : packages) {
                final String path = aPackage.replaceAll("\\.", "/");
                final URL url = classLoader.getResource(path);
                final String filepath = url.toString().substring("file:/".length());
                scanComponent(filepath, ret);
            }
        }
        return ret;
    }

    // 递归扫描
    private void scanComponent(String filepath, List<File> list) throws UnsupportedEncodingException {
        filepath = URLDecoder.decode(filepath, "utf-8");
        File dir = new File(filepath);
        for (File file : dir.listFiles()) {
            if (file.isFile() && file.getName().endsWith(".class")) {
                list.add(file);
            } else if (file.isDirectory()) {
                scanComponent(file.getAbsolutePath(), list);
            } else {
                // 新建的文件对象
//                file.createNewFile();
//                file.mkdirs();
            }
        }
    }

    private void createBeanByBeanMethod() throws Exception {
        final Method[] declaredMethods = configClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            final Bean beanAnnotation = declaredMethod.getAnnotation(Bean.class);
            if (beanAnnotation == null) {
                // 该方法没有加 Bean 注解
            } else {
                // 反射执行方法
                // 强制访问 => 非公有方法
                declaredMethod.setAccessible(true);
                // 创建 bean
                final Object bean = declaredMethod.invoke(config);
                // 获取 bean 的 name
                String[] names;
                if (beanAnnotation.value().length == 0) {
                    names = new String[]{declaredMethod.getName()};
                } else {
                    names = beanAnnotation.value();
                }
                for (String name : names) {
                    BeanInfo bi = new BeanInfo();
                    bi.bean = bean;
                    bi.isPrimary = declaredMethod.getAnnotation(Primary.class) != null;
                    IOC.put(name, bi);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {

        AnnotationConfigApplicationContext cxt =
                new AnnotationConfigApplicationContext(IocConfig.class);

        cxt.IOC.forEach((name, bean) -> {
            System.out.printf(" %s : %s \n", name, bean);
        });
    }

    public Object getBean(String name) {
        final BeanInfo bi = IOC.get(name);
        if (bi.bean == null) {
            throw new RuntimeException("没有该bean: " + name);
        }
        return bi.bean;
    }

    public <T> T getBean(Class<T> cls) {
        final Collection<BeanInfo> beanInfos = IOC.values();
        List<BeanInfo> list = new ArrayList<>();
        // 查找符合的bean
        for (BeanInfo beanInfo : beanInfos) {
            if (beanInfo.bean.getClass().equals(cls)) {
                list.add(beanInfo);
            }
        }
        if (list.isEmpty()) {
            throw new RuntimeException("没有该bean: " + cls);
        } else if (list.size() == 1) {
            return (T) list.get(0).bean;
        } else {
            Object primaryBean = null;
            // 找 primary 主bean
            for (BeanInfo beanInfo : list) {
                if (beanInfo.isPrimary) {
                    primaryBean = beanInfo.bean;
                }
            }
            if (primaryBean == null) {
                throw new RuntimeException("容器中该类的bean包含多个: " + cls);
            } else {
                return (T) primaryBean;
            }
        }
    }

    private BeanInfo buildBeanInfo(Class beanClass) throws Exception {
        boolean isPrimary = beanClass.getAnnotation(Primary.class) != null;
        BeanInfo beanInfo = new BeanInfo();
        beanInfo.isPrimary = isPrimary;
        beanInfo.bean = beanClass.newInstance();
        // TODO 扩展其他属性
        return beanInfo;
    }

}

class BeanInfo {
    Object bean;
    boolean isPrimary;
    boolean isLazy;
    Class BeanClass;
    String scope = "singleton"; // prototype 多列
    // ...
}
