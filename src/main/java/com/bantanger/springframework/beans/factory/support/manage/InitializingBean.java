package com.bantanger.springframework.beans.factory.support.manage;

import com.bantanger.springframework.beans.exception.BeansException;

/**
 * @author BanTanger 半糖
 * @Date 2023/2/9 22:02
 */
public interface InitializingBean {

    /**
     * Bean 处理了属性填充后调用
     *
     * @throws BeansException
     */
    void afterPropertiesSet() throws BeansException;

}
