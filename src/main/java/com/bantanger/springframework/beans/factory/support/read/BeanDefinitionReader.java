package com.bantanger.springframework.beans.factory.support.read;

import com.bantanger.springframework.beans.exception.BeansException;
import com.bantanger.springframework.beans.factory.config.definition.BeanDefinitionRegistry;
import com.bantanger.springframework.core.io.load.ResourceLoader;
import com.bantanger.springframework.core.io.resource.Resource;

/**
 * Bean 定义读取接口
 * 读取 Spring 配置文件中的内容，将其解析到 BeanDefinition 并注册到 BeanDefinitionRegistry 工厂
 * @author BanTanger 半糖
 * @Date 2023/2/7 14:41
 */
public interface BeanDefinitionReader {

    /**
     * 统一约定 bean 定义信息注册接口
     * 实现需要包装到抽象类中，防止污染具体接口实现方法
     * @return bean定义注册工厂
     */
    BeanDefinitionRegistry getRegister();

    /**
     * 统一约定资源加载器
     * 实现需要包装到抽象类中，防止污染具体接口实现方法
     * @return 根据所给的资源文件路径地址返回对应加载的 Resource
     */
    ResourceLoader getResourceLoader();

    /**
     * 单个资源加载 Bean 定义方法
     * @param resource 资源
     * @throws BeansException 自定义 Bean 异常
     */
    void loadBeanDefinitions(Resource resource) throws BeansException;

    /**
     * 多个资源加载 Bean 定义方法
     * @param resources 资源列表
     * @throws BeansException 自定义 Bean 异常
     */
    void loadBeanDefinitions(Resource... resources) throws BeansException;

    /**
     * 指定资源路径加载 Bean 定义方法
     * @param location 资源路径
     * @throws BeansException 自定义 Bean 异常
     */
    void loadBeanDefinitions(String location) throws BeansException;

    /**
     * 指定资源路径加载 Bean 定义方法
     * @param locations 资源路径列表
     * @throws BeansException 自定义 Bean 异常
     */
    void loadBeanDefinitions(String... locations) throws BeansException;

}
