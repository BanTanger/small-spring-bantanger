package com.bantanger.springframework.test;

import com.bantanger.springframework.context.support.ClassPathXmlApplicationContext;
import com.bantanger.springframework.test.event.CustomEvent;
import org.junit.Test;

/**
 * @author BanTanger 半糖
 * @Date 2023/2/6 17:24
 */
public class ApiTest {

    @Test
    public void test_event() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext, 1019129009086763L, "成功了"));

        applicationContext.registerShutdownHook();
    }

}
