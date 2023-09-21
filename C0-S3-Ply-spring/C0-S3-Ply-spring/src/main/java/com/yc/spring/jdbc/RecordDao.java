package com.yc.spring.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class RecordDao {
    @Resource
    JdbcTemplate jdbc;
    public int insert (int id,double money){
        return 0;
    }
}
