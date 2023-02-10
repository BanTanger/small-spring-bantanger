package com.bantanger.springframework.beans.factory.config;

import com.bantanger.springframework.beans.factory.HierarchicalBeanFactory;
import com.bantanger.springframework.beans.factory.config.processor.BeanPostProcessor;
import com.bantanger.springframework.beans.factory.support.singleton.SingletonBeanRegistry;

/**
 * beanFactory 扩展子接口
 * 可获取 BeanPostProcessor 后处理器、BeanClassLoader 类加载器等的一个配置化接口
 * @author BanTanger 半糖
 * @Date 2023/2/7 21:59
 */
public interface ConfigurableBeanFactory extends SingletonBeanRegistry, HierarchicalBeanFactory {

    /**
     * 单例作用域标识
     */
    String SCOPE_SINGLETON = "singleton";

    /**
     * 原型作用域标识
     */
    String SCOPE_PROTOTYPE = "prototype";

    /**
     * 添加处理 bean 的后处理器
     * BeanFactory 容器注册 bean 后处理器必须通过代码显式注册，此为注册方法。
     * @param beanPostProcessor
     */
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * 销毁单例对象
     */
    void destroySingletons();

}
