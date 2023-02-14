package com.bantanger.springframework.context.support;

import com.bantanger.springframework.beans.exception.BeansException;
import com.bantanger.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.bantanger.springframework.beans.factory.config.processor.BeanFactoryPostProcessor;
import com.bantanger.springframework.beans.factory.config.processor.BeanPostProcessor;
import com.bantanger.springframework.context.ApplicationListener;
import com.bantanger.springframework.context.ConfigurableApplicationContext;
import com.bantanger.springframework.context.event.*;
import com.bantanger.springframework.core.io.load.impl.DefaultResourceLoader;

import java.util.Collection;
import java.util.Map;

/**
 * 应用上下文抽象类实现
 * 使用模板模式统一核心方法 refresh 的执行顺序
 * @author BanTanger 半糖
 * @Date 2023/2/7 23:49
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    public static final String APPLICATION_EVENT_MULTICASTER_BASE_NAME = "applicationEventMulticaster";

    private ApplicationEventMulticaster applicationEventMulticaster;

    @Override
    public void refresh() throws BeansException {
        // 1. 创建 BeanFactory, 并加载 BeanDefinition
        refreshBeanFactory();

        // 2. 获取能分析和修改 Bean 以及预先实例化的 BeanFactory 工厂
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 3. Spring 添加 ApplicationContextAwareProcessor，让继承了 ApplicationContextAware 的 Bean 对象都能感知所属的 ApplicationContext
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        // 4. 在 Bean 实例化之前，执行 BeanFactoryPostProcessor，进而获取或修改 beanDefinition 配置元数据
        invokeBeanFactoryPostProcessors(beanFactory);

        // 5. BeanPostProcessor 需要提前于其他的 Bean 对象实例化之前执行注册操作 (执行于 bean 对象实例化之后)
        registerBeanPostProcessors(beanFactory);

        // 6. 初始化事件发布者
        initApplicationEventMulticaster();

        // 7. 注册事件监听器
        registerListeners();

        // 8. 提前实例化单例 Bean 对象 (非懒加载的单例 Bean 对象)
        beanFactory.preInstantiateSingletons();

        // 9. 发布容器刷新完成事件
        finishRefresh();
    }

    private void initApplicationEventMulticaster() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        // 将监听器注册为单例 bean 对象,在 Spring 里，所有对象都使用 bean 管理
        beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BASE_NAME, applicationEventMulticaster);
    }

    /**
     * 注册事件监听器
     */
    private void registerListeners() {
        // 通过 getBeansOfType 方法获取道所有在 spring.xml 中加载到的事件配置 Bean 对象
        Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
        for (ApplicationListener listener : applicationListeners) {
            applicationEventMulticaster.addApplicationLister(listener);
        }
    }

    private void finishRefresh() {
        // 通知所有监听器，服务器已经启动并完成刷新容器的事件，这个事件通过 publishEvent 发布出去
        publishEvent(new ContextRefreshedEvent(this));
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
    }

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        // 发布容器关闭事件
        publishEvent(new ContextClosedEvent(this));

        // 执行销毁单例 bean 的销毁方法
        getBeanFactory().destroySingletons();
    }

    /**
     * 创建 BeanFactory 以及加载 BeanDefinition
     * @throws BeansException
     */
    protected abstract void refreshBeanFactory() throws BeansException;

    /**
     * 提供已经注册好的 BeanFactory 工厂
     * 即：已经读取配置信息并将 Bean 定义信息所注册到 BeanFactory 中管理
     * @return
     */
    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    /**
     * 调用在上下文中注册为 bean 的工厂处理器
     * @param beanFactory
     */
    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        // 获取 bean 工厂后处理器集合
        Map<String, BeanFactoryPostProcessor> beanFactoryProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryProcessorMap.values()) {
            // 将 bean 工厂后处理器加入
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    /**
     * BeanFactory 容器显式注册 bean 后置处理器
     * @param beanFactory
     */
    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        // 获取 bean 后处理器集合
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return getBeanFactory().getBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name, requiredType);
    }

}
