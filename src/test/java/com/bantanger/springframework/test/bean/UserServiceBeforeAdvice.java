package com.bantanger.springframework.test.bean;

import com.bantanger.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author BanTanger 半糖
 * @Date 2023/2/16 19:36
 */
public class UserServiceBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("拦截方法:" + method.getName());
    }

}
