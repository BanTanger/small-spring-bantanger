package com.bantanger.springframework.beans.factory;

import com.bantanger.springframework.beans.exception.BeansException;
import com.bantanger.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.bantanger.springframework.beans.factory.config.BeanDefinition;

/**
 * 提供分析和修改Bean以及预先实例化的操作接口
 * @author BanTanger 半糖
 * @Date 2023/2/7 21:54
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory {

    /**
     * 使用 Bean 名称查询 BeanDefinition
     *
     * @param beanName Bean 对象名称
     * @return Bean 对象定义信息
     * @throws BeansException 自定义 Bean 异常
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

}
