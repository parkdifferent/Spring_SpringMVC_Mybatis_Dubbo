package com.szkingdom.ssm.controller;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.szkingdom.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by tianf on 2016/10/29.
 */

@Controller
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IUserService userService;




    @RequestMapping(value="/hello",produces="text/html;charset=UTF-8" )
    @ResponseBody
    public String hello() {
        return "hello world,I love you";

    }

    @RequestMapping("/list")
    public String userList() {
        return null;
    }





}
