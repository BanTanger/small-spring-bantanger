package com.bantanger.springframework.context.support;

import com.bantanger.springframework.beans.exception.BeansException;
import com.bantanger.springframework.beans.factory.config.processor.BeanPostProcessor;
import com.bantanger.springframework.context.ApplicationContext;
import com.bantanger.springframework.context.ApplicationContextAware;

/**
 * Spring 内置实现的一个后置处理器，用于获取 ApplicationContext
 * @author BanTanger 半糖
 * @Date 2023/2/10 21:13
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ApplicationContextAware) {
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}
