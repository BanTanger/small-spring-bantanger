package com.bantanger.springframework.context;

import com.bantanger.springframework.beans.exception.BeansException;

/**
 * 可配置的应用上下文接口
 * Spring中实现客制化的接口，例如关于刷新容器方面的操作、注册虚拟机钩子的操作，手动关闭虚拟机的操作
 * @author BanTanger 半糖
 * @Date 2023/2/7 23:47
 */
public interface ConfigurableApplicationContext extends ApplicationContext{

    /**
     * 核心方法，实现在上下文的实现中完成初始化容器的操作过程，也就是把 bean 加入到了 Spring IoC 容器中
     * 初始化容器的操作包括：
     * 1. 创建和获取 beanFactory、
     * 2. 加载 beanDefinition、
     * 3. 注册 BeanFactoryPostProcessor、BeanPostProcessor 后置处理器
     * 4. 提前实例化单例 Bean 对象
     * @throws BeansException
     */
    void refresh() throws BeansException;

    /**
     * 注册虚拟机钩子实现自动关闭, 本质上还是调用 close() 方法
     * 内部细节是向 Java虚拟机 JVM 注册 shutdown 钩子事件。待程序一结束就会运行线程 Hook
     */
    void registerShutdownHook();

    /**
     * 手动关闭应用上下文 Application 的接口方法
     * 如果不想用 Hook 关闭，可以自己调用 ApplicationContext.close() 完成关闭操作
     */
    void close();

}
