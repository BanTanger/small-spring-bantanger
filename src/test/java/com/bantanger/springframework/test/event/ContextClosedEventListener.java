package com.bantanger.springframework.test.event;

import com.bantanger.springframework.context.ApplicationListener;
import com.bantanger.springframework.context.event.ContextClosedEvent;

/**
 * @author BanTanger 半糖
 * @Date 2023/2/13 16:46
 */
public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("关闭事件：" + this.getClass().getName());
    }

}
