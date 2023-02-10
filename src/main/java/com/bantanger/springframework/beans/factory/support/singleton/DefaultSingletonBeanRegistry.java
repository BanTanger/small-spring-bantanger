package com.bantanger.springframework.beans.factory.support.singleton;

import com.bantanger.springframework.beans.exception.BeansException;
import com.bantanger.springframework.beans.factory.support.manage.DisposableBean;
import com.bantanger.springframework.beans.factory.support.singleton.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Bean 对象默认使用单例模式注册获取以及销毁操作
 *
 * @author BanTanger 半糖
 * @Date 2023/2/6 19:42
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private final Map<String, Object> singletonObjects = new HashMap<>();

    private final Map<String, DisposableBean> disposableBeanMap = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    /**
     * bean对象存入单例对象缓存容器
     *
     * @param beanName        bean对象名称
     * @param singletonObject 单例对象
     */
    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }

    public void registerDisposableBean(String beanName, DisposableBean bean) {
        // 将暴露的 bean（其实本质是适配器，内部存储了 bean 信息） 存入集合中。之后由虚拟机统一销毁
        disposableBeanMap.put(beanName, bean);
    }

    public void destroySingletons() {
        Set<String> keySet = this.disposableBeanMap.keySet();
        Object[] disposableBeanNames = keySet.toArray();
        for(int i = disposableBeanNames.length - 1; i >= 0; i --) {
            Object disposableBeanName = disposableBeanNames[i];
            DisposableBean disposableBean = disposableBeanMap.remove(disposableBeanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeansException("Destroy method on bean with name '" + disposableBeanName + "' threw an exception", e);
            }
        }
    }

}
