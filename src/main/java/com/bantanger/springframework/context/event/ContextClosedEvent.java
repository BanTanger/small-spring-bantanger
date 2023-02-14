package com.bantanger.springframework.context.event;

/**
 * 监听关闭动作
 * @author BanTanger 半糖
 * @Date 2023/2/11 15:20
 */
public class ContextClosedEvent extends ApplicationContextEvent {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextClosedEvent(Object source) {
        super(source);
    }

}
