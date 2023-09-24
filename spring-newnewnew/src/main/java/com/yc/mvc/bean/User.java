package com.yc.mvc.bean;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class User {
    String name;
    String pwd;
    Character gender;
    Date birthday;
    int age;
    String [] likes;
    List<String> types;

}
