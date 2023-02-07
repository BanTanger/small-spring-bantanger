package com.bantanger.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.bantanger.springframework.beans.BeansException;
import com.bantanger.springframework.beans.PropertyValue;
import com.bantanger.springframework.beans.PropertyValues;
import com.bantanger.springframework.beans.factory.config.BeanDefinition;
import com.bantanger.springframework.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;

/**
 * 抽象类 AbstractAutowireCapableBeanFactory
 * 实例化 Bean 类
 *
 * @author BanTanger 半糖
 * @Date 2023/2/6 19:54
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = null;
        try {
            // 从 BeanDefinition 获取 Bean 的类信息，实例化完整的类: 有参无参均可
            bean = createBeanInstance(beanDefinition, beanName, args);
            // 属性注入
            applyPropertyValues(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed -- 实例化容器失败", e);
        }
        // 将注册好的实例 Bean 存入单例对象缓存中
        addSingleton(beanName, bean);
        return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
        Constructor constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        // 获取 bean 对象构造器列表
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor<?> ctor : declaredConstructors) {
            if (null != args && ctor.getParameterTypes().length == args.length) {
                constructorToUse = ctor;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
    }

    /**
     * Bean 属性填充
     *
     * @param beanName       bean对象名称
     * @param bean           bean对象
     * @param beanDefinition bean对象定义
     */
    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {

                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                if (value instanceof BeanReference) {
                    // 如果遇到的是 BeanReference 类型的 value，说明是非基本类型，需要递归获取 Bean 实例，调用 getBean 方法
                    // A 依赖 B，先获取 B 的对象实例
                    BeanReference beanReference = (BeanReference) value;
                    // 递归获取 Bean 实例
                    value = getBean(beanReference.getBeanName());
                }
                // TODO 属性填充，并没有处理循环依赖的问题
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e) {
            throw new BeansException("Error setting property values：" + beanName
                    + " -- " + beanName + " 的属性值设置错误");
        }
    }

    protected InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

}
