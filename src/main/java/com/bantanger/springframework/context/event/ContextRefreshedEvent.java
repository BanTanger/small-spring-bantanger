package com.bantanger.springframework.context.event;

/**
 * 监听刷新动作
 * @author BanTanger 半糖
 * @Date 2023/2/11 15:21
 */
public class ContextRefreshedEvent extends ApplicationContextEvent {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextRefreshedEvent(Object source) {
        super(source);
    }

}
