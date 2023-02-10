package com.bantanger.springframework.context;

import com.bantanger.springframework.beans.exception.BeansException;

/**
 * 能感知到所属的 ApplicationContext
 * @author BanTanger 半糖
 * @Date 2023/2/10 21:12
 */
public interface ApplicationContextAware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;

}
