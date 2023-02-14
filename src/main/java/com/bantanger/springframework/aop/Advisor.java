package com.bantanger.springframework.aop;

import org.aopalliance.aop.Advice;

/**
 * 定义 Advisor 访问者
 * @author BanTanger 半糖
 * @Date 2023/2/14 16:38
 */
public interface Advisor extends Advice {

    /**
     * 返回 Advice。Advice 可以是 interceptor 、before advice 、throws advice 等。
     * @return
     */
    Advice getAdvice();

}
