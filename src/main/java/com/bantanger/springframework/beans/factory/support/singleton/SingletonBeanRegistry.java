package com.bantanger.springframework.beans.factory.support.singleton;

/**
 * 定义单例 bean 对象接口
 *
 * @author BanTanger 半糖
 * @Date 2023/2/6 19:40
 */
public interface SingletonBeanRegistry {

    /**
     * 单例对象获取
     *
     * @param beanName bean 对象名称
     * @return 单例对象
     */
    Object getSingleton(String beanName);

    void registerSingleton(String beanName, Object singletonObject);

}
