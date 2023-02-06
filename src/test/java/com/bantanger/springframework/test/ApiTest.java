package com.bantanger.springframework.test;

import com.bantanger.springframework.factory.config.BeanDefinition;
import com.bantanger.springframework.factory.support.DefaultListableBeanFactory;
import com.bantanger.springframework.test.bean.UserService;
import org.junit.Test;

/**
 * @author BanTanger 半糖
 * @Date 2023/2/6 17:24
 */
public class ApiTest {

    @Test
    public void test_BeanFactory() {
        // 1. 初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 注册 Bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 3. 无参 Bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();

        // 4. 有参 Bean 有 bug, bean 没法区分有参无参
        UserService userService_args = (UserService) beanFactory.getBean("userService", "半糖");
        userService_args.queryUserInfo();
    }

}
