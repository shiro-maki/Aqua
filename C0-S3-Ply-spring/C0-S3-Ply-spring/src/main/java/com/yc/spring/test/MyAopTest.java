package com.yc.spring.test;

import com.yc.spring.AOPApplicationContext;
import com.yc.spring.AopConfig;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;

public class MyAopTest {
    @Test
    public void test() throws Exception {
        AOPApplicationContext cxt=new AOPApplicationContext(AopConfig.class);
    }
}
