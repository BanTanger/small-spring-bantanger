package com.bantanger.springframework.beans.factory.config.processor;

import com.bantanger.springframework.beans.exception.BeansException;

/**
 * {@link BeanPostProcessor} 的子接口，用于添加实例化前的回调，以及在实例化之后但在设置显式属性或自动连线之前添加回调。
 * @author BanTanger 半糖
 * @Date 2023/2/14 17:29
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    /**
     * 在 Bean 对象执行实例化（初始化）之前，执行此方法
     * 执行完该方法后，调用方获取到的 Bean 对象就是一个已经被切面注入的对象了，当调用方法的时候，则会被按需拦截，处理用户需要的信息
     * @param beanClass
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;

}
