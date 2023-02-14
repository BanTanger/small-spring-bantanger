package com.bantanger.springframework.aop;

/**
 * PointcutAdvisor 承担了 Pointcut 和 Advice 的组合
 * Pointcut 用于获取 JoinPoint，而 Advice 决定于 JoinPoint 执行什么操作
 * @author BanTanger 半糖
 * @Date 2023/2/14 17:04
 */
public interface PointcutAdvisor extends Advisor {

    /**
     * 获取切入点 JoinPoint
     * @return
     */
    Pointcut getPointcut();

}
