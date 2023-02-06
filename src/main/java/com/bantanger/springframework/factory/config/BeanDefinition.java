package com.bantanger.springframework.factory.config;

/**
 * 用于定义 Bean 实例化信息 Spring容器只存储 Bean 对象类型信息
 * @author BanTanger 半糖
 * @Date 2023/2/6 17:14
 */
@SuppressWarnings({"rawtypes"})
public class BeanDefinition {

    private Class beanClass;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

}
