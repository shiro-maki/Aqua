package com.yc.spring.aop;

public class Proxy {
    public static void main(String[] args) {
        Person p1=new 被告();
        律师 p2=new 律师();
        p2.proxy(p1);
        p2.say();
    }
}

interface Person{
    void say();

}

class 被告 implements Person{

    @Override
    public void say() {
        System.out.println("我是无辜的");
    }
}
class 律师 implements Person{
    Person person;

    @Override
    public void say() {
        System.out.println("被告不在场!");
        person.say();
        System.out.println("被告是精神病");
    }

    public void  proxy(Person person){
        this.person=person;
    }
}