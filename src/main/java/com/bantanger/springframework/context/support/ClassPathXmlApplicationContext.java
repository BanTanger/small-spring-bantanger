package com.bantanger.springframework.context.support;

import com.bantanger.springframework.beans.exception.BeansException;

/**
 * 应用上下文实现类, 对外给用户提供应用上下文方法
 * 独立的 XML 应用程序上下文，从类路径中获取上下文定义文件，
 * 将纯路径解释为包含包路径的类路径资源名称（例如“mypackagemyresource.txt”）。
 * 对于测试工具以及嵌入在 JAR 中的应用程序上下文非常有用。
 *
 * @author BanTanger 半糖
 * @Date 2023/2/8 16:58
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {

    private String[] configLocations;

    public ClassPathXmlApplicationContext() {
    }

    /**
     * 从 XML 中加载 BeanDefinition，并刷新上下文
     */
    public ClassPathXmlApplicationContext(String configLocations) throws BeansException {
        this(new String[]{configLocations});
    }

    /**
     * 从 XML 中加载 BeanDefinition，并刷新上下文
     */
    public ClassPathXmlApplicationContext(String[] configLocations) throws BeansException {
        this.configLocations = configLocations;
        refresh();
    }

    @Override
    protected String[] getConfigLocations() {
        return configLocations;
    }

}
