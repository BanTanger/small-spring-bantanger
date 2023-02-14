package com.bantanger.springframework.test.bean;

import com.bantanger.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * 与上一章节的拦截方法相比，我们不在是实现 MethodInterceptor 接口，而是实现 MethodBeforeAdvice 环绕拦截。
 * 在这个方法中我们可以获取到方法的一些信息，如果还开发了它的 MethodAfterAdvice 则可以两个接口一起实现
 * @author BanTanger 半糖
 * @Date 2023/2/14 18:40
 */
public class UserServiceBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("拦截方法：" + method.getName());
    }

}
