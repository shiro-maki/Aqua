package com.yc.mvc.web;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.awt.*;

@RestController
public class IndexAction {
    @RequestMapping("hello")
    public String hello(String name){
        return "hello" +name;
    }
    @RequestMapping("toIndex")
    public ModelAndView toIndex(ModelAndView mav){
        mav.setViewName("/WEB-INF/index.jsp");
        return mav;
    }

    @RequestMapping("toBaidu")
    public ModelAndView toBaidu(ModelAndView mav){
        mav.setViewName("redirect:http://www.baidu.com");
        return mav;
    }

    @RequestMapping(path="toPost",method = RequestMethod.POST)
    public String toPost(){
        return "post";
    }

    @RequestMapping(value="showName",params = "name",headers = "Host=localHost")
    public String showName(String name){
        return name;
    }
    @RequestMapping( path="reg",consumes = MediaType.APPLICATION_JSON_VALUE)
    public String reg(String name,String pwd){
        return "reg";
    }

    @RequestMapping(path = "login",produces = MediaType.TEXT_HTML_VALUE+";charset=utf-8")
    public String login(){
        return "<h1>登陆成功</h1>";
    }
}
