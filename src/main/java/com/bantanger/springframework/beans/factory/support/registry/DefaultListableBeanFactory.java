package com.bantanger.springframework.beans.factory.support.registry;

import com.bantanger.springframework.beans.exception.BeansException;
import com.bantanger.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.bantanger.springframework.beans.factory.config.definition.BeanDefinition;
import com.bantanger.springframework.beans.factory.config.processor.BeanPostProcessor;
import com.bantanger.springframework.beans.factory.support.instantiate.AbstractAutowireCapableBeanFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 核心类 DefaultListableBeanFactory
 * 具备获取 Bean 和注册 Bean 的功能
 *
 * @author BanTanger 半糖
 * @Date 2023/2/6 20:07
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {

    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) {
            throw new BeansException("No bean named '" + beanName + "' is defined" + " -- '" + beanName + "' 没有被定义");
        }
        return beanDefinition;
    }

    @Override
    public void preInstantiateSingletons() throws BeansException {
        // 获取 map 中的所有键，循环使用 getBean 方法加载 bean 信息
        beanDefinitionMap.keySet().forEach(this::getBean);
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return beanDefinitionMap.keySet().toArray(new String[0]);
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        Map<String, T> result = new HashMap<>();
        beanDefinitionMap.forEach((beanName, beanDefinition) -> {
            Class beanClass = beanDefinition.getBeanClass();
            // isAssignableFrom() 方法是用于检查一个类是否是另一个类的父类或接口的实现
            // instanceof 运算符则是用于检查一个对象是否是另一个类的实例
            if (type.isAssignableFrom(beanClass)) {
                result.put(beanName, (T) getBean(beanName));
            }
        });
        return result;
    }

}
