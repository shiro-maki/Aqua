package com.yc.cinema.test;

import com.yc.cinema.IocConfig;
import com.yc.cinema.bean.Cinema;
import com.yc.cinema.bean.Hall;
import com.yc.cinema.bean.Movie;
import com.yc.cinema.bean.User;
import com.yc.cinema.biz.ActorBiz;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = IocConfig.class)
public class BeanTest {
    @Test
    public void test1(){
        ClassPathXmlApplicationContext cxt=new ClassPathXmlApplicationContext("beans.xml");
        User user =cxt.getBean(User.class);
        System.out.println(user);
        User user2 = (User) cxt.getBean("user");
        System.out.println(user2.getUsername());
        User user3 = (User) cxt.getBean("user1");
        System.out.println(user3.getUsername());
        User user4 = (User) cxt.getBean("user2");
        System.out.println(user4.getUsername());

        User user5 = (User) cxt.getBean("user3");
        User user6 = (User) cxt.getBean("user3");
        User user7 = (User) cxt.getBean("user3");
        System.out.println(user5==user6);

        final Movie bean = cxt.getBean(Movie.class);
        Assert.assertEquals("赵六", bean.getUser().getUsername());
        cxt.close();
    }
    @Test
    public void test(){
        AnnotationConfigApplicationContext cxt=new AnnotationConfigApplicationContext(IocConfig.class);
        User user1=cxt.getBean(User.class);
        System.out.println(user1);

        User user2 = (User) cxt.getBean("user");
        User user3=(User) cxt.getBean("user1");
        System.out.println(user3.getUsername());
        User user5 = (User) cxt.getBean("user3");
        User user6 = (User) cxt.getBean("user3");
        System.out.println(user2.getUsername());
        System.out.println(user5==user6);
    }

    @Autowired
    @Qualifier("myCinema")
    Cinema cinema;
    @Autowired
    Hall hall;

    @Resource
    Cinema myCinema;
    @Resource
    Cinema cinema1;

    @Resource
    ActorBiz actorBiz;

    @Resource
    ActorBiz myActorBiz;
    @Test
    public void test2(){
        Assert.assertNotNull(cinema);
        Assert.assertNotNull(myCinema);
//        Assert.assertNull(cinema1);
        Assert.assertEquals(cinema.getHall(), hall);
//        Assert.assertNull(cinema.getName());
        Assert.assertEquals("红旗电影院",cinema.getName());
        Assert.assertNull(actorBiz.getAdao());
        Assert.assertNotNull(myActorBiz.getAdao());
    }
}
