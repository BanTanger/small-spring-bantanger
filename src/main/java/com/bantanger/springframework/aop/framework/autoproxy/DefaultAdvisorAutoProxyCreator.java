package com.bantanger.springframework.aop.framework.autoproxy;

import com.bantanger.springframework.aop.*;
import com.bantanger.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import com.bantanger.springframework.aop.framework.ProxyFactory;
import com.bantanger.springframework.beans.exception.BeansException;
import com.bantanger.springframework.beans.factory.BeanFactory;
import com.bantanger.springframework.beans.factory.aware.BeanFactoryAware;
import com.bantanger.springframework.beans.factory.config.definition.PropertyValues;
import com.bantanger.springframework.beans.factory.config.processor.InstantiationAwareBeanPostProcessor;
import com.bantanger.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 融入Bean生命周期的自动代理创建者
 * @author BanTanger 半糖
 * @Date 2023/2/14 17:28
 */
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private DefaultListableBeanFactory beanFactory;

    private final Set<Object> earlyProxyReference = Collections.synchronizedSet(new HashSet<>());

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        return true;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues propertyValues, Object bean, String beanName) {
        return propertyValues;
    }

    /**
     * 先判断当前 bean 是否为基础结构类：Advice、Pointcut、Advisor。
     * 通过 isAssignableFrom(beanClass) 进行判断
     * 只有 UserService 这种类型的数据结构才能够被代理。
     * @param beanClass
     * @return
     */
    private boolean isInfrastructureClass(Class<?> beanClass) {
        return Advice.class.isAssignableFrom(beanClass) || Pointcut.class.isAssignableFrom(beanClass) || Advisor.class.isAssignableFrom(beanClass);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (!earlyProxyReference.contains(beanName)) {
            return wrapIfNecessary(bean, beanName);
        }
        return bean;
    }

    /**
     * 创建 AOP 代理
     * 操作由 postProcessBeforeInstantiation 转移到 postProcessAfterInstantiation
     * @param bean
     * @param beanName
     * @return 返回代理对象 new ProxyFactory(advisedSupport).getProxy()
     */
    protected Object wrapIfNecessary(Object bean, String beanName) {
        if (isInfrastructureClass(bean.getClass())) {
            return bean;
        }
        Collection<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class).values();

        for (AspectJExpressionPointcutAdvisor advisor : advisors) {
            ClassFilter classFilter = advisor.getPointcut().getClassFilter();
            // 过滤不匹配的类型
            if (!classFilter.matches(bean.getClass())) {
                continue ;
            }
            AdvisedSupport advisedSupport = new AdvisedSupport();
            TargetSource targetSource = new TargetSource(bean);
            // 填充对应属性信息：目标对象、拦截方法、匹配器
            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            advisedSupport.setProxyTargetClass(true);

            // 返回代理对象
            return new ProxyFactory(advisedSupport).getProxy();
        }
        return bean;
    }

    @Override
    public Object getEarlyBeanReference(Object bean, String beanName) {
        earlyProxyReference.add(beanName);
        return wrapIfNecessary(bean, beanName);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}
