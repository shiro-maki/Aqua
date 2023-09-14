package com.yc.spring.aop;

import org.springframework.stereotype.Service;

@Service
public class SubClass implements Parent{
    @Override
    public void test() {
        System.out.println("------------SubClass.test()");
    }
}



