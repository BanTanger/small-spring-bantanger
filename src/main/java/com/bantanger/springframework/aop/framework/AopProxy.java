package com.bantanger.springframework.aop.framework;

/**
 * AOP 代理的对象
 * @author BanTanger 半糖
 * @Date 2023/2/13 22:36
 */
public interface AopProxy {

    /**
     * 定义一个标准接口，用于获取代理类。
     * 因为具体实现代理的方式可以有 JDK 方式，也可以是 Cglib 方式，所以定义接口会更加方便管理实现类。
     * @return
     */
    Object getProxy();

}
