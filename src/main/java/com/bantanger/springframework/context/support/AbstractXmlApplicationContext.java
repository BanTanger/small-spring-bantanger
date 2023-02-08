package com.bantanger.springframework.context.support;

import com.bantanger.springframework.beans.factory.support.registry.DefaultListableBeanFactory;
import com.bantanger.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 上下文中对配置信息的加载
 * 用于 {@link com.bantanger.springframework.context.ApplicationContext} 实现的方便基类
 * 使用 {@link com.bantanger.springframework.beans.factory.xml.XmlBeanDefinitionReader}
 * 在 XML 文档中读取并配置 Bean 定义.
 *
 * @author BanTanger 半糖
 * @Date 2023/2/8 15:00
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        // 1. 从 XML 读取 bean 配置信息
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        // 2. 获取配置信息的地址描述
        String[] configLocations = getConfigLocations();
        // 3. 将配置信息加载至 beanDefinition 中
        if (null != configLocations) {
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    /**
     * 从入口上下文类，拿到配置信息的地址描述
     * @return
     */
    protected abstract String[] getConfigLocations();

}
