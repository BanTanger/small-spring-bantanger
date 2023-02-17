package com.bantanger.springframework.beans.factory.support.instantiate;

import com.bantanger.springframework.beans.exception.BeansException;
import com.bantanger.springframework.beans.factory.config.ConfigurableBeanFactory;
import com.bantanger.springframework.beans.factory.config.definition.BeanDefinition;
import com.bantanger.springframework.beans.factory.config.processor.BeanPostProcessor;
import com.bantanger.springframework.beans.factory.support.factorybean.FactoryBean;
import com.bantanger.springframework.beans.factory.support.factorybean.FactoryBeanRegisterSupport;
import com.bantanger.springframework.beans.factory.support.singleton.DefaultSingletonBeanRegistry;
import com.bantanger.springframework.util.ClassUtils;
import com.bantanger.springframework.util.StringValueResolver;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象类实现 BeanFactory, 实现了 ConfigurableBeanFactory
 * 具有增添和获取 Bean 后置处理器的功能
 *
 * @author BanTanger 半糖
 * @Date 2023/2/6 19:45
 */
public abstract class AbstractBeanFactory extends FactoryBeanRegisterSupport implements ConfigurableBeanFactory {

    /**
     * ClassLoader to resolve bean class names with, if necessary
     */
    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

    /**
     * BeanPostProcessors to apply in createBean
     */
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    /**
     * String resolvers to apply e.g. to annotation attribute values
     */
    private final List<StringValueResolver> embeddedValueResolvers = new ArrayList<>();

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
        Object sharedInstance = getSingleton(name);
        if (sharedInstance != null) {
            // 如果是 FactoryBean，则需要调用 FactoryBean#getObject
            return (T) getObjectForBeanInstance(sharedInstance, name);
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        Object bean = createBean(name, beanDefinition, args);
        return (T) getObjectForBeanInstance(bean, name);
    }

    private Object getObjectForBeanInstance(Object beanInstance, String beanName) {
        if (!(beanInstance instanceof FactoryBean)) {
            return beanInstance;
        }

        Object cachedObjectForFactoryBean = getCachedObjectForFactoryBean(beanName);

        // 如果不存在 Object，调用 getObjectFromFactoryBean 获取 Object
        if (cachedObjectForFactoryBean == null) {
            FactoryBean<?> factoryBean = (FactoryBean<?>) beanInstance;
            cachedObjectForFactoryBean = getObjectFromFactoryBean(factoryBean, beanName);
        }
        return cachedObjectForFactoryBean;
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

    public ClassLoader getBeanClassLoader() {
        return this.beanClassLoader;
    }

    @Override
    public void addEmbeddedValueResolver(StringValueResolver stringValueResolver) {
        this.embeddedValueResolvers.add(stringValueResolver);
    }

    @Override
    public String resolveEmbeddedValue(String value) {
        String result = value;
        for (StringValueResolver embeddedValueResolver : this.embeddedValueResolvers) {
            result = embeddedValueResolver.resolveStringValue(result);
        }
        return result;
    }

}
