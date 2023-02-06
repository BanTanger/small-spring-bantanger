package com.bantanger.springframework.factory.support;

import com.bantanger.springframework.BeansException;
import com.bantanger.springframework.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * 定义 Bean 实例化策略接口
 *
 * @author BanTanger 半糖
 * @Date 2023/2/6 21:57
 */
@SuppressWarnings({"rawtypes"})
public interface InstantiationStrategy {

    /**
     * bean 实例化策略接口,有参无参构造器都能实例化对象
     *
     * @param beanDefinition bean对象定义信息
     * @param beanName       bean对象名称
     * @param ctor           bean对象具体构造器
     * @param args           bean对象构造器参数列表
     * @return bean实例化对象
     * @throws BeansException
     */
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException;

}
