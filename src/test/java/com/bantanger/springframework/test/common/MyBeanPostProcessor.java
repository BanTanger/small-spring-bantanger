package com.bantanger.springframework.test.common;

import com.bantanger.springframework.beans.exception.BeansException;
import com.bantanger.springframework.beans.factory.config.processor.BeanPostProcessor;
import com.bantanger.springframework.test.bean.UserService;

/**
 * @author BanTanger 半糖
 * @Date 2023/2/8 17:29
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("userService".equals(beanName)) {
            UserService userService = (UserService) bean;
            userService.setLocation("广东东莞");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}
