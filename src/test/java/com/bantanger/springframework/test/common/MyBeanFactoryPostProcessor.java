package com.bantanger.springframework.test.common;

import com.bantanger.springframework.beans.exception.BeansException;
import com.bantanger.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.bantanger.springframework.beans.factory.config.definition.BeanDefinition;
import com.bantanger.springframework.beans.factory.config.definition.PropertyValue;
import com.bantanger.springframework.beans.factory.config.definition.PropertyValues;
import com.bantanger.springframework.beans.factory.config.processor.BeanFactoryPostProcessor;

/**
 * @author BanTanger 半糖
 * @Date 2023/2/8 17:26
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();

        propertyValues.addPropertyValue(new PropertyValue("company", "阿里巴巴"));
    }

}
