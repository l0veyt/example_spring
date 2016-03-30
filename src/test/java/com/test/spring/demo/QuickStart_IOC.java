package com.test.spring.demo;

import com.test.spring.service.UserService;
import com.test.spring.service.impl.UserServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Xin.Lee on 2016/3/31.
 * IOC快速入门演示
 */
public class QuickStart_IOC {

    /**
     * 传统方式
     * 如果要更换UserService的实现类就必须要对代码进行改动
     * 如果使用spring管理则只需要修改applicationContext.xml中的bean对应的class路径
     */
    @Test
    public void demo0() {
        UserService userService = new UserServiceImpl();
        userService.sayHello();
    }

    /**
     * IOC：控制反转，即创建对象的控制权被反转给了spring
     */
    @Test
    public void demo1() {
        // 通过加载配置文件，解析xml文件，创建工厂类
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 根据id获取相对应的bean得到class路径
        // 再通过Class.forName()反射得到userService的class类
        // 再通过new Instance()得到相应的对象(如果构造器是私有的就使用暴力反射)
        UserService userService = (UserService) context.getBean("userService");
        // 执行相应的方法
        userService.sayHello();
    }

    @Test
    public void demo2() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        // spring 相当于做了这样的事情
        Class<?> clazz = Class.forName("com.test.spring.service.impl.UserServiceImpl");
        UserService userService = (UserService) clazz.newInstance();
        userService.sayHello();
    }

    @Test
    public void demo3() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // 如果构造器私有化了额，则使用Declare来暴力获取
        Class<?> clazz = Class.forName("com.test.spring.service.impl.UserServiceImpl");
        Constructor<?> c = clazz.getDeclaredConstructor();
        c.setAccessible(true);
        UserService userService = (UserService) c.newInstance();
        userService.sayHello();
    }
}
