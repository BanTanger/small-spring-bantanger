package com.bantanger.springframework.aop;

import com.bantanger.springframework.util.ClassUtils;

/**
 * @author BanTanger 半糖
 * @Date 2023/2/13 22:23
 */
public class TargetSource {

    private final Object target;

    public TargetSource(Object target) {
        this.target = target;
    }

    /**
     * getTargetClass 是用于获取 target 对象的接口信息的，
     * 那么这个 target 可能是 JDK代理 创建也可能是 CGlib创建，
     * 为了保证都能正确的获取到结果，这里需要增加判读 ClassUtils.isCglibProxyClass(clazz)
     * @return
     */
    public Class<?>[] getTargetClass() {
        Class<?> clazz = this.target.getClass();
        clazz = ClassUtils.isCglibProxyClass(clazz) ? clazz.getSuperclass() : clazz;
        return clazz.getInterfaces();
    }

    /**
     * 返回目标实例。在 AOP 框架调用 AOP 方法调用的“目标”之前立即调用。
     * @return
     */
    public Object getTarget() {
        return this.target;
    }
}
