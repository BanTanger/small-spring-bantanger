package com.bantanger.springframework.aop;

import java.lang.reflect.Method;

/**
 * 方法匹配器
 * 找到表达式范围内匹配下的目标类和方法。
 * @author BanTanger 半糖
 * @Date 2023/2/13 20:37
 */
public interface MethodMatcher {

    /**
     * 执行静态检查给定方法是否匹配。
     * @param method
     * @param targetClass
     * @return
     */
    boolean matches(Method method, Class<?> targetClass);

}
