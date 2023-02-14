package com.bantanger.springframework.context.event;

import com.bantanger.springframework.beans.exception.BeansException;
import com.bantanger.springframework.beans.factory.BeanFactory;
import com.bantanger.springframework.beans.factory.aware.BeanFactoryAware;
import com.bantanger.springframework.context.ApplicationListener;
import com.bantanger.springframework.util.ClassUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * 抽象事件广播器
 * 对事件广播器的公用方法提取，在这个类中可以实现一些基本功能，避免所有直接实现接口放还需要处理细节。
 * 除了像 addApplicationListener、removeApplicationListener 这样的通用方法
 * 这里这个类中主要是对 getApplicationListeners 和 supportsEvent 的处理。
 *
 * @author BanTanger 半糖
 * @Date 2023/2/11 15:34
 */
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {

    public final Set<ApplicationListener<ApplicationEvent>> applicationListeners = new LinkedHashSet<>();

    private BeanFactory beanFactory;

    @Override
    public void addApplicationLister(ApplicationListener<?> listener) {
        applicationListeners.add((ApplicationListener<ApplicationEvent>) listener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.remove(listener);
    }

    @Override
    public final void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    /**
     * 将所有事件监听器加入链表中
     * @param event
     * @return
     */
    protected Collection<ApplicationListener> getApplicationListeners(ApplicationEvent event) {
        LinkedList<ApplicationListener> allListeners = new LinkedList<>();
        for (ApplicationListener<ApplicationEvent> listener : applicationListeners) {
            if (supportsEvent(listener, event)) {
                allListeners.add(listener);
            }
        }
        return allListeners;
    }

    /**
     * 监听器是否对该事件感兴趣
     * @param applicationListener
     * @param event
     * @return
     */
    protected boolean supportsEvent(ApplicationListener<ApplicationEvent> applicationListener, ApplicationEvent event) {
        Class<? extends ApplicationListener> listenerClass = applicationListener.getClass();

        /*
         按照 CglibSubclassingInstantiationStrategy、SimpleInstantiationStrategy 不同的实例化类型
         需要判断后获取目标 class，Cglib 代理类需要获取父类的 Class，普通实例化的不需要
         */
        Class<?> targetClass = ClassUtils.isCglibProxyClass(listenerClass) ? listenerClass.getSuperclass() : listenerClass;
        // 获取该目标类的泛型接口
        Type genericInterface = targetClass.getGenericInterfaces()[0];

        // 获取该实际类型参数的类名
        Type actualTypeArgument = ((ParameterizedType) genericInterface).getActualTypeArguments()[0];
        String className = actualTypeArgument.getTypeName();
        Class<?> eventClassName;
        try {
            eventClassName = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new BeansException("wrong event class name:" + className);
        }
        /*
        判定此 eventClassName 对象所表示的类或接口与指定的 event.getClass() 参数所表示的类或接口是否相同，或是否是其超类或超接口。
        isAssignableFrom 是用来判断子类和父类的关系的，或者接口的实现类和接口的关系的，默认所有的类的终极父类都是 Object。
        如果 A.isAssignableFrom(B)结果是 true，证明 B 可以转换成为 A,也就是 A可以由 B 转换而来。
         */
        return eventClassName.isAssignableFrom(event.getClass());
    }

}
