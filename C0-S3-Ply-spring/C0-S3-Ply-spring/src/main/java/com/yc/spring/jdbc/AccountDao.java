package com.yc.spring.jdbc;

import com.yc.spring.bank.bean.Account;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class AccountDao {
    @Resource
    JdbcTemplate jdbc;
    public void insert(int id,double money,String password){
        String sql="insert into bank_account values(?,?,?)";
        jdbc.update(sql,id,password,money);

    }

    public void update(int id,double money){
        String sql="update bank_account set balance =balance + ? where id= ?";
        jdbc.update(sql,money,id);

    }

    public Account selectByID(int id){
        String sql="select * from bank_account where id=?";
        RowMapper<Account> rm=new BeanPropertyRowMapper<>(Account.class);
        return jdbc.queryForObject(sql,rm ,id);
    }
}
