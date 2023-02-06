package com.bantanger.springframework.factory.support;

import com.bantanger.springframework.BeansException;
import com.bantanger.springframework.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * 核心类 DefaultListableBeanFactory
 * 具备获取 Bean 和注册 Bean 的功能
 * @author BanTanger 半糖
 * @Date 2023/2/6 20:07
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {

    private final Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    protected BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) {
            throw new BeansException("No bean named '" + beanName + "' is defined" + " -- '" + beanName + "' 没有被定义");
        }
        return beanDefinition;
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }
}
