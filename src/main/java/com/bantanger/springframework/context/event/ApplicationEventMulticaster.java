package com.bantanger.springframework.context.event;

import com.bantanger.springframework.context.ApplicationListener;

/**
 * 事件广播器(管理所有事件监听器)
 * 消息 --> 事件发布者推送 --> 事件广播器接收 --> 通知所有事件监听器接收
 * @author BanTanger 半糖
 * @Date 2023/2/11 15:26
 */
public interface ApplicationEventMulticaster {

    /**
     * 添加事件监听器
     * @param listener
     */
    void addApplicationLister(ApplicationListener<?> listener);

    /**
     * 移除事件监听器
     * @param listener
     */
    void removeApplicationListener(ApplicationListener<?> listener);

    /**
     * 广播事件
     * 最终推送时间消息也会经过这个接口方法来处理谁该接收事件。
     * @param event
     */
    void multicastEvent(ApplicationEvent event);

}
