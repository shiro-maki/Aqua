package com.yc.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
@ComponentScan("com.yc.spring")
@Configuration
@EnableAspectJAutoProxy
public class AopConfig {

}
