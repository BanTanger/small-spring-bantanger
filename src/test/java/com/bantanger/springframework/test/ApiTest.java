package com.bantanger.springframework.test;

import com.bantanger.springframework.context.support.ClassPathXmlApplicationContext;
import com.bantanger.springframework.test.bean.Husband;
import com.bantanger.springframework.test.bean.Wife;
import org.junit.Test;

/**
 * @author BanTanger 半糖
 * @Date 2023/2/6 17:24
 */
public class ApiTest {

    @Test
    public void test_circular() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        Husband husband = applicationContext.getBean("husband", Husband.class);
        Wife wife = applicationContext.getBean("wife", Wife.class);
        System.out.println("老公的媳妇：" + husband.queryWife());
        System.out.println("媳妇的老公：" + wife.queryHusband());
    }

}
