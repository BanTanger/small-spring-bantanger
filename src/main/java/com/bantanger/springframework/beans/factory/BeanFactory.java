package com.bantanger.springframework.beans.factory;

import com.bantanger.springframework.beans.exception.BeansException;

/**
 * 定义 Bean 工厂职责，实现交给具体实现类
 *
 * @author BanTanger 半糖
 * @Date 2023/2/6 17:14
 */
public interface BeanFactory {

    Object getBean(String name) throws BeansException;

    Object getBean(String name, Object... args) throws BeansException;

    <T> T getBean(String name, Class<T> requiredType) throws BeansException;

    <T> T getBean(Class<T> requiredType) throws BeansException;

}
