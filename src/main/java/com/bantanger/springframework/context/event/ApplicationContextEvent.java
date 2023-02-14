package com.bantanger.springframework.context.event;

import com.bantanger.springframework.context.ApplicationContext;

/**
 * 定义事件类，所有事件包括关闭、刷新、用户自己实现的事件都需要继承这个类
 * @author BanTanger 半糖
 * @Date 2023/2/11 14:17
 */
public class ApplicationContextEvent extends ApplicationEvent {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }

}
