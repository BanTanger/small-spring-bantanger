package com.bantanger.springframework.beans.factory.support.factorybean;

import com.bantanger.springframework.beans.exception.BeansException;

/**
 * @author BanTanger 半糖
 * @Date 2023/2/10 23:31
 */
public interface FactoryBean<T> {

    T getObject() throws BeansException;

    Class<?> getObjectType();

    boolean isSingleton();

}
