package com.bantanger.springframework.beans.factory.config.processor;

import com.bantanger.springframework.beans.exception.BeansException;
import com.bantanger.springframework.beans.factory.ConfigurableListableBeanFactory;

/**
 * 允许自定义修改 BeanDefinition 属性信息
 * 提供修改 BeanDefinition 属性的机制，生命周期：在所有的 BeanDefinition 加载完成后，实例化 Bean 对象之前
 * @author BanTanger 半糖
 * @Date 2023/2/7 23:38
 */
public interface BeanFactoryPostProcessor {

    /**
     * 在所有的 BeanDefinition 加载完成后，实例化 Bean 对象之前，提供修改 BeanDefinition 属性的机制
     *
     * @param beanFactory
     * @throws BeansException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;

}
