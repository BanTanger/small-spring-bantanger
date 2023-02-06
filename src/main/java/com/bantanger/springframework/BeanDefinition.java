package com.bantanger.springframework;

/**
 * 用于定义 Bean 实例化信息，现在的实现是以一个 Object 存放对象
 * @author BanTanger 半糖
 * @Date 2023/2/6 17:14
 */
public class BeanDefinition {

    private Object bean;

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public Object getBean() {
        return bean;
    }

}
