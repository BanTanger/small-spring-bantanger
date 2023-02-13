package com.bantanger.springframework.context;

import com.bantanger.springframework.context.event.ApplicationEvent;

import java.util.EventListener;

/**
 * 事件监听者
 * 由应用程序事件侦听器实现的接口。基于标准 java.util.EventListener 接口的观察器设计模式。
 * @author BanTanger 半糖
 * @Date 2023/2/11 15:28
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    /**
     * 监听器在接受到发布消息后各自需要完成的逻辑
     * @param event the event to respond to
     */
    void onApplicationEvent(E event);

}
