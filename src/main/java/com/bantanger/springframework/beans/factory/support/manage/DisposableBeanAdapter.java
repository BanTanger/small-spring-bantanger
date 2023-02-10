package com.bantanger.springframework.beans.factory.support.manage;

import cn.hutool.core.util.StrUtil;
import com.bantanger.springframework.beans.exception.BeansException;
import com.bantanger.springframework.beans.factory.config.definition.BeanDefinition;

import java.lang.reflect.Method;

/**
 * @author BanTanger 半糖
 * @Date 2023/2/10 9:29
 */
public class DisposableBeanAdapter implements DisposableBean {

    private final Object bean;

    private final String beanName;

    private String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        // 检查当前 bean 有无实现 DisposableBean 接口
        if (bean instanceof DisposableBean) {
            ((DisposableBean) bean).destroy();
        }

        // 如果没有实现接口，检查注解配置有无 destroy-method {判断是为了避免二次执行销毁}
        if (StrUtil.isNotEmpty(destroyMethodName)
                && !(bean instanceof DisposableBean
                && "destroy".equals(this.destroyMethodName))) {
            Method destroyMethod = bean.getClass().getMethod(destroyMethodName);
            if (null == destroyMethod) {
                throw new BeansException("Couldn't find a destroy method named '" + destroyMethodName + "' on bean with name '" + beanName + "'");
            }
            destroyMethod.invoke(bean);
        }
    }
}
