package com.bantanger.springframework.beans.factory.support.instantiate;

import com.bantanger.springframework.beans.exception.BeansException;
import com.bantanger.springframework.beans.factory.config.ConfigurableBeanFactory;
import com.bantanger.springframework.beans.factory.config.definition.BeanDefinition;
import com.bantanger.springframework.beans.factory.config.processor.BeanPostProcessor;
import com.bantanger.springframework.beans.factory.support.singleton.DefaultSingletonBeanRegistry;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象类实现 BeanFactory, 实现了 ConfigurableBeanFactory
 * 具有增添和获取 Bean 后置处理器的功能
 *
 * @author BanTanger 半糖
 * @Date 2023/2/6 19:45
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    /** BeanPostProcessors to apply in createBean */
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name, null);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }


    protected <T> T doGetBean(final String name, final Object[] args) {
        Object bean = getSingleton(name);
        if (bean != null) {
            // 单例 bean 对象已经存在于缓存中，直接获取返回
            return (T) bean;
        }

        // 获取不到 bean，说明是第一次注册，通过 bean 的定义完成实例化操作
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return (T) createBean(name, beanDefinition, args);
    }

    /**
     * 获取 Bean 定义信息
     *
     * @param beanName Bean 对象名称
     * @return Bean 定义信息
     * @throws BeansException 自定义 Bean 异常
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * Bean 的实例化, 有参无参均可
     *
     * @param beanName       Bean 对象名称
     * @param beanDefinition Bean 定义信息
     * @param args           参数列表
     * @return 实例化的 Bean
     * @throws BeansException 自定义 Bean 异常
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        // 先 remove 再 add，删除旧值，添加新值，确保只有一个实例
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    /**
     * Return the list of BeanPostProcessors that will get applied
     * to beans created with this factory.
     */
    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }

}
