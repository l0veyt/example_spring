package com.test.spring.service.impl;

import com.test.spring.service.OrderService;

/**
 * Created by Xin.Lee on 2016/3/31.
 * 订单service层实现类
 */
public class OrderServiceImpl implements OrderService {

    private String info;

    public void setInfo(String info) {
        this.info = info;
    }

    public void sayInfo() {
        System.out.println("hello " + info);
    }

}
