package com.bantanger.springframework.beans.factory;

import com.bantanger.springframework.beans.BeansException;

/**
 * 定义 Bean 工厂职责，实现交给具体实现类
 *
 * @author BanTanger 半糖
 * @Date 2023/2/6 17:14
 */
public interface BeanFactory {

    /**
     * 获取 Bean 实例对象
     *
     * @param name 注册 Bean 的实例对象名称
     * @return Bean 实例对象
     * @throws BeansException 自定义 Bean 异常处理
     */
    Object getBean(String name) throws BeansException;

    /**
     * 获取 Bean 实例对象
     *
     * @param name 注册 Bean 的实例对象名称
     * @param args 有参构造的参数列表
     * @return Bean 实例对象
     * @throws BeansException 自定义 Bean 异常处理
     */
    Object getBean(String name, Object... args) throws BeansException;

}
