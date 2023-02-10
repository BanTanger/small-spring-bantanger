package com.bantanger.springframework.beans.factory.config.processor;

import com.bantanger.springframework.beans.exception.BeansException;

/**
 * 用于修改新实例化 Bean 对象的扩展点
 *
 * @author BanTanger 半糖
 * @Date 2023/2/7 23:38
 */
public interface BeanPostProcessor {

    /**
     * 实例化、依赖注入完毕，在调用显示的初始化之前完成一些定制的初始化任务
     *
     * @param bean
     * @param beanName
     * @return 修改后的 Bean 对象
     * @throws BeansException
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * 实例化、依赖注入、初始化完毕时执行
     *
     * @param bean
     * @param beanName
     * @return 修改后的 Bean 对象
     * @throws BeansException
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;

}
