package com.bantanger.springframework.context;

import com.bantanger.springframework.context.event.ApplicationEvent;

/**
 * 事件发布者接口，所有的事件都需要经过此接口发布
 * @author BanTanger 半糖
 * @Date 2023/2/11 15:30
 */
public interface ApplicationEventPublisher {

    /**
     * 通知在此应用程序中注册的所有侦听器应用程序事件。
     * 事件可以是框架事件（如 RequestHandledEvent）或特定于应用程序的事件。
     * @param event 发布事件
     */
    void publishEvent(ApplicationEvent event);

}
