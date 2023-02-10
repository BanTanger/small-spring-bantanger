package com.bantanger.springframework.beans.factory.support.read.impl;

import com.bantanger.springframework.beans.factory.support.read.BeanDefinitionReader;
import com.bantanger.springframework.beans.factory.config.definition.BeanDefinitionRegistry;
import com.bantanger.springframework.core.io.load.ResourceLoader;
import com.bantanger.springframework.core.io.load.impl.DefaultResourceLoader;

/**
 * Bean 定义抽象类实现
 * 实现了 BeanDefinitionReader，具有注册 Bean 定义的 Bean 工厂、资源加载器
 * @author BanTanger 半糖
 * @Date 2023/2/7 14:59
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    /**
     * 注册 Bean 定义的 Bean 工厂
     */
    private final BeanDefinitionRegistry registry;

    /**
     * 资源加载器
     */
    private final ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry, new DefaultResourceLoader());
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public BeanDefinitionRegistry getRegister() {
        return registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

}
