package com.yc.spring.test;

import com.yc.spring.JdbcConfig;
import com.yc.spring.bank.bean.Account;
import com.yc.spring.jdbc.AccountDao;
import com.yc.spring.jdbc.BankBiz;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = JdbcConfig.class)
public class JdbcTest {
    @Resource
    AccountDao accountDao;
    @Test
    public void test1(){
        accountDao.insert(123, 10000d,"123");
        final Account account = accountDao.selectByID(123);
        System.out.println("account = " + account);
        Assert.assertEquals(new Double(10000d), account.getBalance());

    }

    @Resource
    BankBiz biz;
    @Test
    public void test2(){
        biz.deposit(123456,5000);
        final Account account=accountDao.selectByID(123456);


    }

}
