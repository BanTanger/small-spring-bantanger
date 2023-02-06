package com.bantanger.springframework.factory.support;

import com.bantanger.springframework.BeanFactory;
import com.bantanger.springframework.BeansException;
import com.bantanger.springframework.factory.config.BeanDefinition;

/**
 * 抽象类实现 BeanFactory, 使用模板模式统一通用核心方法调度
 *
 * @author BanTanger 半糖
 * @Date 2023/2/6 19:45
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name, null);
    }
    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name, args);
    }

    protected <T> T doGetBean(final String name, final Object[] args) {
        Object bean = getSingleton(name);
        if (bean != null) {
            // 单例 bean 对象已经存在于缓存中，直接获取返回
            return (T) bean;
        }

        // 获取不到 bean，说明是第一次注册，通过 bean 的定义完成实例化操作
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return (T) createBean(name, beanDefinition, args);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;
}
