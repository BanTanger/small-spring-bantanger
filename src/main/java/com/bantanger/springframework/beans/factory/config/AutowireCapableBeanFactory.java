package com.bantanger.springframework.beans.factory.config;

import com.bantanger.springframework.beans.exception.BeansException;
import com.bantanger.springframework.beans.factory.BeanFactory;

/**
 * 自动化处理 Bean 工厂配置的接口
 * 不在 IoC 容器的 Bean 也可以被 Spring 管理：集成其它框架，像集成 WebWork 的 Actions、Tapestry Page
 * @author BanTanger 半糖
 * @Date 2023/2/7 21:57
 */
public interface AutowireCapableBeanFactory extends BeanFactory {

    /**
     * 执行 BeanPostProcessors 接口实现类的 postProcessBeforeInitialization 方法
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorBeforeInitialization(Object existingBean, String beanName) throws BeansException;

    /**
     * 执行 BeanPostProcessors 接口实现类的 postProcessorsAfterInitialization 方法
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorAfterInitialization(Object existingBean, String beanName) throws BeansException;

}
