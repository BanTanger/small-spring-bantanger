package com.bantanger.springframework.context;

import com.bantanger.springframework.beans.exception.BeansException;

/**
 * 应用上下文接口
 * 关于刷新容器方面的操作
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

}
