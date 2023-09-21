package com.yc.spring.jdbc;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BankBiz {
    @Resource
    AccountDao adao;
    @Resource
    RecordDao rdao;

    public void deposit(int id,double money){

        //TODO 业务判断
        adao.update(id,money);
        rdao.insert(id, money);
    }

}
