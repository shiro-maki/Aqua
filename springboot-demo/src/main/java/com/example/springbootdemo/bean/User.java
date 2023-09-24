package com.example.springbootdemo.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
@ConfigurationProperties(prefix="demo")
@Data
public class User {
    String myName;
    String name;
    int age;
    String[] likes;
    List<String> schools;
    List<User> friends;
    Map<String,String> familys;
    List<Map<String, Object>> dogs;
}
