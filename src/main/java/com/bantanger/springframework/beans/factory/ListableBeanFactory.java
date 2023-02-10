package com.bantanger.springframework.beans.factory;

import com.bantanger.springframework.beans.exception.BeansException;

import java.util.Map;

/**
 * 由 Bean 工厂实现的 {@link BeanFactory} 接口的扩展，
 * 该 Bean 工厂可以枚举其所有 Bean 实例，而不是按照客户端的请求尝试按名称逐个查找 Bean。
 * 预加载所有 Bean 定义的 BeanFactory 实现（例如基于 XML 的工厂）可以实现此接口
 *
 * 在 Spring 源码中还有其他扩展方法。
 * @author BanTanger 半糖
 * @Date 2023/2/7 21:49
 */
public interface ListableBeanFactory extends BeanFactory {

    /**
     * 按照类型返回 Bean 实例
     * 用于解决不能区分有参无参的 bug
     * @param type bean 对象参数类型
     * @param <T> 泛型
     * @return bean 实例集合
     * @throws BeansException 自定义 Bean 异常
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * 获取注册表中所有的 Bean 名称
     * @return 注册表中所有的 Bean 名称列表
     */
    String[] getBeanDefinitionNames();

}
