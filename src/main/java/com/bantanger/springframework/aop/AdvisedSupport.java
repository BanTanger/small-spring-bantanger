package com.bantanger.springframework.aop;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * 包装切面通知信息
 * 主要是用于把代理、拦截、匹配的各项属性包装到一个类中，方便在 Proxy 实现类进行使用。这和你的业务开发中包装入参是一个道理
 * @author BanTanger 半糖
 * @Date 2023/2/13 22:23
 */
public class AdvisedSupport {

    /**
     * ProxyConfig
     */
    private boolean proxyTargetClass = false;

    /**
     * 被代理的目标对象
     * 主要是用于把代理、拦截、匹配的各项属性包装到一个类中，方便在 Proxy 实现类进行使用。
     * 这和你的业务开发中包装入参是一个道理
     */
    private TargetSource targetSource;

    /**
     * 方法拦截器
     * 一个具体拦截方法实现类，由用户自己实现 MethodInterceptor#invoke 方法
     * 用于做方法监控
     */
    private MethodInterceptor methodInterceptor;

    /**
     * 方法匹配器（检查目标方法是否符合通知条件）
     * 这个对象由 AspectJExpressionPointcut 提供服务
     */
    private MethodMatcher methodMatcher;

    public boolean isProxyTargetClass() {
        return proxyTargetClass;
    }

    public void setProxyTargetClass(boolean proxyTargetClass) {
        this.proxyTargetClass = proxyTargetClass;
    }

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }
}
