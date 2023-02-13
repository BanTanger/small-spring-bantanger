package com.bantanger.springframework.test.event;

import com.bantanger.springframework.context.event.ApplicationContextEvent;

/**
 * 自定义事件,在事件类的构造函数中可以添加自己的想要的入参信息。
 * 这个事件类最终会被完成的拿到监听里，所以你添加的属性都会被获得到。
 * @author BanTanger 半糖
 * @Date 2023/2/13 16:46
 */
public class CustomEvent extends ApplicationContextEvent {

    private Long id;
    private String message;

    public CustomEvent(Object source, Long id, String message) {
        super(source);
        this.id = id;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
