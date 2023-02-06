package com.bantanger.springframework;

/**
 * 定义 Bean 统一异常
 * @author BanTanger 半糖
 * @Date 2023/2/6 18:07
 */
public class BeansException extends RuntimeException {

    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
