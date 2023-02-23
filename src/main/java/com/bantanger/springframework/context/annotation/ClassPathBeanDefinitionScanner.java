package com.bantanger.springframework.context.annotation;

import cn.hutool.core.util.StrUtil;
import com.bantanger.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import com.bantanger.springframework.beans.factory.config.definition.BeanDefinition;
import com.bantanger.springframework.beans.factory.config.definition.BeanDefinitionRegistry;
import com.bantanger.springframework.stereotype.Component;

import java.util.Set;

/**
 * 处理对象扫描装配
 * @author BanTanger 半糖
 * @Date 2023/2/15 17:00
 */
public class ClassPathBeanDefinitionScanner extends ClassPathScanningCandidateComponentProvider {

    private final String AUTOWIRED_ANNOTATION = "com.bantanger.springframework.beans.factory.config.processor.InstantiationAwareBeanPostProcessor";

    private BeanDefinitionRegistry registry;

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public void doScan(String... basePackages) {
        // 处理 @Component
        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidate = findCandidateComponents(basePackage);
            for (BeanDefinition beanDefinition : candidate) {
                // 解析 Bean 的作用域是 singleton 还是 prototype
                String beanScope = resolveBeanScope(beanDefinition);
                if (StrUtil.isNotEmpty(beanScope)) {
                    beanDefinition.setScope(beanScope);
                }
                // 解析 beanDefinition 的 beanName(小写开头) 并将其注册 BeanDefinition
                registry.registerBeanDefinition(determineBeanName(beanDefinition), beanDefinition);
            }
        }

        // 处理 @Autowired、@Value
        registry.registerBeanDefinition(AUTOWIRED_ANNOTATION, new BeanDefinition(AutowiredAnnotationBeanPostProcessor.class));
    }

    private String resolveBeanScope(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Scope scope = beanClass.getAnnotation(Scope.class);
        if (null != scope) {
            return scope.value();
        }
        return StrUtil.EMPTY;
    }

    private String determineBeanName(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Component component = beanClass.getAnnotation(Component.class);
        String value = component.value();
        if (StrUtil.isEmpty(value)) {
            value = StrUtil.lowerFirst(beanClass.getSimpleName());
        }
        return value;
    }
}
