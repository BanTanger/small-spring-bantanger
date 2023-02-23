package com.bantanger.springframework.context.annotation;

import cn.hutool.core.util.ClassUtil;
import com.bantanger.springframework.beans.factory.config.definition.BeanDefinition;
import com.bantanger.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 处理对象扫描装配
 * @author BanTanger 半糖
 * @Date 2023/2/15 17:00
 */
public class ClassPathScanningCandidateComponentProvider {

    /**
     * 扫描所有标记了 @Component 注解的 Bean 对象，将类型信息 Class 存入 BeanDefinition
     * @param basePackage
     * @return
     */
    public Set<BeanDefinition> findCandidateComponents(String basePackage) {
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        for (Class<?> clazz : classes) {
            candidates.add(new BeanDefinition(clazz));
        }
        return candidates;
    }

}
