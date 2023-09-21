package com.yc.spring;

import com.yc.spring.test.JdbcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@ComponentScan("com.yc.spring")
public class JdbcConfig {
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dmds=new DriverManagerDataSource();
        dmds.setUrl("jdbc:mysql:///mybank");
        dmds.setDriverClassName("com.mysql.jdbc.Driver");
        dmds.setUsername("root");
        dmds.setPassword("root");
        return dmds;
    }
    @Resource
    DataSource dataSource;

    @Bean
    public JdbcTemplate jdbcTemplate(){
        JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }

}
