package com.yc.mvc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("a/b/c")
public class DemoController {
    @RequestMapping("hello.do")
    public String hello(String name){
        return "hello" +name;
    }
    @RequestMapping("toTaobao")
    public String toTaobao(){
        return "redirect:http://www.taobao.com";
    }
}
