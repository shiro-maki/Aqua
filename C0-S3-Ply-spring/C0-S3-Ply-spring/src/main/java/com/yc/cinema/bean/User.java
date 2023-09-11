package com.yc.cinema.bean;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Date;
@Component("user1")

public class User {
    private Integer id;

    private String username;

    private String password;

    private String email;

    private String headImg;

    private String phone;

    private Integer status;

    private Date regtime;
    //静态工厂方法
    public static User create(){
        final User user=new User();
        user.setUsername("张三");
        return user;
    }
    public User newInstance(){
        final User user=new User();
        user.setUsername("李四");
        return user;
    }

    public User() {
    }

    public User(String username) {
        this.username = username;
    }
    @PostConstruct
    public void a(){
        System.out.println("User对象呗创建");
    }
    @PreDestroy
    public void b(){
        System.out.println("User对象呗销毁");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg == null ? null : headImg.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getRegtime() {
        return regtime;
    }

    public void setRegtime(Date regtime) {
        this.regtime = regtime;
    }
}