package com.bantanger.springframework.beans.factory;

import com.bantanger.springframework.beans.exception.BeansException;
import com.bantanger.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.bantanger.springframework.beans.factory.config.ConfigurableBeanFactory;
import com.bantanger.springframework.beans.factory.config.definition.BeanDefinition;
import com.bantanger.springframework.beans.factory.config.processor.BeanPostProcessor;

/**
 * beanFactory 扩展子接口
 * 提供分析和修改 Bean 以及预先实例化(解决循环依赖的问题)的操作接口
 *
 * @author BanTanger 半糖
 * @Date 2023/2/7 21:54
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    /**
     * 使用 Bean 名称查询 BeanDefinition
     *
     * @param beanName Bean 对象名称
     * @return Bean 对象定义信息
     * @throws BeansException 自定义 Bean 异常
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 预先实例化(解决循环依赖的问题)
     * 确保所有非懒加载单例 bean 被初始化，包括工厂 bean，如果需要，在 bean 工厂设置后，调用此方法。
     * @throws BeansException 如果任何一个单例 bean 不能够创建，将抛出 BeansException
     */
    void preInstantiateSingletons() throws BeansException;

    @Override
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

}
