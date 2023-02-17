package com.bantanger.springframework.test;

import com.bantanger.springframework.context.support.ClassPathXmlApplicationContext;
import com.bantanger.springframework.test.bean.IUserService;
import org.junit.Test;

/**
 * @author BanTanger 半糖
 * @Date 2023/2/6 17:24
 */
public class ApiTest {

    @Test
    public void test_autoProxy() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println("测试结果：" + userService.queryUserInfo());
    }

}
