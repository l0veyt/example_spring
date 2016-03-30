package com.test.spring.demo;

import com.test.spring.service.OrderService;
import com.test.spring.service.impl.OrderServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Xin.Lee on 2016/3/31.
 * DI快速入门演示
 */
public class QuickStart_DI {

    /**
     * 传统方式
     * 如果要为对象的info属性设置值，必须调用相应的set方法或修改set方法内的参数值
     * 如果使用spring管理则只需要修改或添加applicationContext.xml中的bean的property
     */
    @Test
    public void demo0() {
        OrderService orderService = new OrderServiceImpl();
        orderService.sayInfo();
    }

    /**
     * DI：依赖注入，即spring创建对象过程中，将这个对象依赖的属性注入进来
     * 通过在applicationContext.xml配置文件中添加property来完成属性的注入
     * 依赖注入必须要去对象提供公有的调用属性的方法，即要注入的属性的get/set方法必须是公有的
     */
    @Test
    public void demo1() {
        // 通过加载配置文件，解析xml文件，创建工厂类
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 根据id获取相对应的bean得到class路径
        // 再通过Class.forName()反射得到OrderService的class类
        // 再通过new Instance()得到相应的对象(如果构造器是私有的就使用暴力反射)
        OrderService orderService = (OrderService) context.getBean("orderService");
        // 执行相应的方法
        orderService.sayInfo();
    }
}
