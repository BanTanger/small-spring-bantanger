package com.bantanger.springframework.factory.support;

import com.bantanger.springframework.factory.config.BeanDefinition;

/**
 * bean对象定义信息注册接口
 *
 * @author BanTanger 半糖
 * @Date 2023/2/6 20:05
 */
public interface BeanDefinitionRegistry {

    /**
     * 像注册表中注册 BeanDefinition
     *
     * @param beanName       bean对象名称
     * @param beanDefinition bean对象定义信息
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

}
