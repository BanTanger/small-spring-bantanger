package com.bantanger.springframework.factory.support;

import com.bantanger.springframework.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * Bean 对象默认使用单例模式获取
 * @author BanTanger 半糖
 * @Date 2023/2/6 19:42
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private final Map<String, Object> singletonObjects = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    /**
     * bean对象存入单例对象缓存容器
     * @param beanName bean对象名称
     * @param singletonObject 单例对象
     */
    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }

}
