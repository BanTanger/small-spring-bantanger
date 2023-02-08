package com.bantanger.springframework.context.support;

import com.bantanger.springframework.beans.exception.BeansException;
import com.bantanger.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.bantanger.springframework.beans.factory.support.registry.DefaultListableBeanFactory;

/**
 * {@link com.bantanger.springframework.context.ApplicationContext} 实现的基类，
 * 它应该支持对 {@link #refresh（）} 的多次调用，每次都创建一个新的内部 Bean Factory 实例。
 * 通常（但不一定），这样的上下文将由一组配置位置驱动，以便从中加载 Bean 定义。
 *
 * @author BanTanger 半糖
 * @Date 2023/2/8 12:59
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    /**
     * 获取具有注册获取 bean 的 Bean 工厂 (最底层层级，不使用高级层级达到防污)
     */
    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeansException {
        // 1. 创建工厂 (每次调用都会创建一个新的 Bean 工厂实例)
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        // 2. 读取 xml 资源文件并加载到 beanDefinition 中
        loadBeanDefinitions(beanFactory);
        // 3. 将注册好的 beanFactory 放入内存里
        this.beanFactory = beanFactory;
    }

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    /**
     * 读取 xml 资源文件并加载到 beanDefinition 中
     * @param beanFactory
     */
    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        // 提供已经注册好的 BeanFactory 工厂
        return beanFactory;
    }
}
