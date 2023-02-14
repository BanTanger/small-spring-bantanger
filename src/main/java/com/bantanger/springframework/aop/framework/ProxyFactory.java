package com.bantanger.springframework.aop.framework;

import com.bantanger.springframework.aop.AdvisedSupport;

/**
 * 代理工厂
 * 主要解决的是关于 JDK 和 Cglib 两种代理的选择问题
 * 有了代理工厂就可以按照不同的创建需求进行控制
 * @author BanTanger 半糖
 * @Date 2023/2/14 17:18
 */
public class ProxyFactory {

    private AdvisedSupport advisedSupport;

    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    public Object getProxy() {
        return createAopProxy().getProxy();
    }

    public AopProxy createAopProxy() {
        if (advisedSupport.isProxyTargetClass()) {
            return new Cglib2AopProxy(advisedSupport);
        }
        return new JdkDynamicAopProxy(advisedSupport);
    }

}
