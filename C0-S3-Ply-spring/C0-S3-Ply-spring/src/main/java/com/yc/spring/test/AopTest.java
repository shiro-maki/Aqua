package com.yc.spring.test;

import com.yc.spring.AopConfig;
import com.yc.spring.aop.Parent;
import com.yc.spring.aop.SubClass;
import com.yc.spring.aop.SubClass1;
import com.yc.spring.bbs.bean.User;
import com.yc.spring.bbs.biz.UserBiz;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AopConfig.class)
public class AopTest {
    @Resource
    UserBiz userBiz;
    @Test
    public void test1(){
        User user=new User();
        userBiz.create(user);
        userBiz.modify(user);
    }

    @Resource
    Parent subClass;

    @Resource
    SubClass1 subClass1;

    @Test
    public void test2(){
       subClass.test();
       subClass1.test();
    }
}
