package com.bantanger.springframework.beans.factory.config;

import com.bantanger.springframework.beans.factory.HierarchicalBeanFactory;
import com.bantanger.springframework.beans.factory.support.singleton.SingletonBeanRegistry;

/**
 * 可获取 BeanPostProcessor 后置处理器、BeanClassLoader 类加载器等的一个配置化接口
 * @author BanTanger 半糖
 * @Date 2023/2/7 21:59
 */
public interface ConfigurableBeanFactory extends SingletonBeanRegistry, HierarchicalBeanFactory {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";
}
