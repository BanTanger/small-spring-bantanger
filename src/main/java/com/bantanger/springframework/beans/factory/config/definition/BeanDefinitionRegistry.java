package com.bantanger.springframework.beans.factory.config.definition;

import com.bantanger.springframework.beans.exception.BeansException;
import com.bantanger.springframework.beans.factory.config.definition.BeanDefinition;

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
     * @param beanName       Bean 对象名称
     * @param beanDefinition Bean 对象定义信息
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    /**
     * 使用 Bean 名称查询 BeanDefinition
     *
     * @param beanName Bean 对象名称
     * @return Bean 对象定义信息
     * @throws BeansException 自定义 Bean 异常
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 判断是否包含指定名称的BeanDefinition
     *
     * @param beanName Bean 对象名称
     * @return 包含为 true，否则为 false
     */
    boolean containsBeanDefinition(String beanName);

    /**
     * 返回注册表中所有的 Bean名称
     *
     * @return Bean 对象名称列表
     */
    String[] getBeanDefinitionNames();

}
