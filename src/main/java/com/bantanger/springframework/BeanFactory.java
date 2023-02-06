package com.bantanger.springframework;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 代表了 Bean 对象的工厂，可以存放 Bean 定义到 Map 中以及获取
 * @author BanTanger 半糖
 * @Date 2023/2/6 17:14
 */
public class BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public Object getBean(String name) {
        return beanDefinitionMap.get(name).getBean();
    }

    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(name, beanDefinition);
    }

}
