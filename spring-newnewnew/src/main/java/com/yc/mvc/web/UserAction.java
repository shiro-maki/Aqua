package com.yc.mvc.web;

import com.yc.mvc.bean.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAction {
    @RequestMapping ("register")
    public User register(User user){
      return user;
    }

}
