package com.bantanger.springframework.test.event;

import com.bantanger.springframework.context.ApplicationListener;
import com.bantanger.springframework.context.event.ContextRefreshedEvent;

/**
 * @author BanTanger 半糖
 * @Date 2023/2/13 16:46
 */
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("刷新事件：" + this.getClass().getName());
    }

}
