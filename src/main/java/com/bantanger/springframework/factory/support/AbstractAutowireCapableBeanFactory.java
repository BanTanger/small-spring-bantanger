package com.bantanger.springframework.factory.support;

import com.bantanger.springframework.BeansException;
import com.bantanger.springframework.factory.config.BeanDefinition;

/**
 * 抽象类 AbstractAutowireCapableBeanFactory
 * 实例化 Bean 类
 * @author BanTanger 半糖
 * @Date 2023/2/6 19:54
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        Object bean;
        try {
            // 从 BeanDefinition 获取 Bean 的类信息，实例化完整的类，但只能实例无参构造对象
            bean = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeansException("实例化容器失败 Instantiation of bean failed", e);
        }
        // 将注册好的实例 Bean 存入单例对象缓存中
        addSingleton(beanName, bean);
        return bean;
    }

}
