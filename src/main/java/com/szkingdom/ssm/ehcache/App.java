package com.szkingdom.ssm.ehcache;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.szkingdom.ssm.dao.UserMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by tianf on 2016/10/30.
 */
public class App {
    private static final Logger log = LoggerFactory.getLogger(App.class);


 /*   public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(CachingConfig.class);
        UserMapper obj = (UserMapper) context.getBean("userMapper");

        log.debug("Result : {}", (Throwable) obj.selectUserList());
        log.debug("Result : {}", (Throwable) obj.selectUserList());
        log.debug("Result : {}", (Throwable) obj.selectUserList());

        //shut down the Spring context.
        ((ConfigurableApplicationContext)context).close();

    }*/

}
