package com.bantanger.springframework.aop;

import java.lang.reflect.Method;

/**
 * 在调用方法之前调用的建议。此类建议无法阻止方法调用继续，除非它们抛出 Throwable。
 *
 * 在 Spring 框架中，Advice 都是通过方法拦截器 MethodInterceptor 实现的。
 * 环绕 Advice 类似一个拦截器的链路，Before Advice、After advice等，
 * 不过暂时我们需要那么多就只定义了一个 MethodBeforeAdvice 的接口定义。
 * @author BanTanger 半糖
 * @Date 2023/2/14 16:40
 */
public interface MethodBeforeAdvice extends BeforeAdvice {

    /**
     * 调用给定方法之前的回调
     * @param method
     * @param args
     * @param target
     * @throws Throwable
     */
    void before(Method method, Object[] args, Object target) throws Throwable;

}
