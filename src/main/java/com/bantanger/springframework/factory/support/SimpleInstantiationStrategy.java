package com.bantanger.springframework.factory.support;

import com.bantanger.springframework.BeansException;
import com.bantanger.springframework.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Jvm方式 Bean 实例化策略
 *
 * @author BanTanger 半糖
 * @Date 2023/2/6 22:02
 */
@SuppressWarnings({"rawtypes"})
public class SimpleInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
        Class clazz = beanDefinition.getBeanClass();

        try {
            if (args != null) {
                // 有参构造
                return clazz.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
            } else {
                // 无参构造
                return clazz.getDeclaredConstructor().newInstance();
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new BeansException("Failed to instantiate [" + clazz.getName() + "]"
                    + " -- [" + clazz.getName() + "] 实例化失败", e);
        }
    }

}
