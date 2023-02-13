package com.bantanger.springframework.context.event;

import com.bantanger.springframework.beans.factory.BeanFactory;
import com.bantanger.springframework.context.ApplicationListener;

/**
 * @author BanTanger 半糖
 * @Date 2023/2/13 16:25
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @Override
    public void multicastEvent(ApplicationEvent event) {
        for (final ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }
}
