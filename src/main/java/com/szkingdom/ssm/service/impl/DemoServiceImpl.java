package com.szkingdom.ssm.service.impl;

import com.szkingdom.ssm.service.DemoService;

/**
 * Created by tianf on 2016/10/29.
 */
public class DemoServiceImpl implements DemoService {

    public String sayHello() {
        System.out.println("hello Dubbo!");
        return "hello Dubbo!";
    }
}
