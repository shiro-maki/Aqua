package com.yc.cinema;

import com.yc.cinema.bean.Cinema;
import com.yc.cinema.bean.User;
import com.yc.cinema.biz.ActorBiz;
import com.yc.cinema.dao.ActorDao;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("com.yc.cinema")
public class IocConfig {
    @Bean
    @Primary
    User user(){
        User user=new User();
        user.setUsername("武松");
        return user;
    }

    @Bean("user")
    User user1(){
        User user=new User();
        user.setUsername("武松");
        return user;
    }

    @Bean("user3")
    @Scope("prototype")

    User user3(){
        User user=new User();
        user.setUsername("武松");
        return user;
    }
    @Bean
    Cinema myCinema(){
        Cinema c=new Cinema();
        c.setName("红旗电影院");
        return c;
    }

    @Bean("myActorBiz")
    ActorBiz ActorBiz(){
        ActorBiz c=new ActorBiz();
        c.setAdao(new ActorDao());
        return c;
    }
}
