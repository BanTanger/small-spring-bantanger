package com.bantanger.springframework.context;

import com.bantanger.springframework.beans.factory.HierarchicalBeanFactory;
import com.bantanger.springframework.beans.factory.ListableBeanFactory;
import com.bantanger.springframework.core.io.load.ResourceLoader;

/**
 * 应用上下文接口
 * 继承了关于 BeanFactory 的方法，例如所有 getBean 方法
 * @author BanTanger 半糖
 * @Date 2023/2/7 23:45
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {
}
