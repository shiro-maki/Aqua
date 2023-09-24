package com.example.springbootdemo.web;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.springbootdemo.bean.User;
import com.example.springbootdemo.entity.Account;
import com.example.springbootdemo.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class IndexController {
    @RequestMapping
    public String hello(String name){
        return "你好" +name;
    }

    @Value("${demo.my-name}")
    private String myName;

    @Value("${demo.age}")
    private int age;





    public String myInfo(){
        return myName+":"+age;
    }

    @Resource
    User user;
    @GetMapping("allInfo")
    public User allInfo(){
        return user;
    }

    @Resource
    AccountMapper accountMapper;
    @RequestMapping("queryAccount")
    public List<Account> queryAccount(){
        LambdaQueryWrapper<Account> lqw;
        lqw=new LambdaQueryWrapper<>();
        lqw.gt(Account::getBalance, 10000);
        final List<Account> list = accountMapper.selectList(lqw);
        return list;
    }


}
