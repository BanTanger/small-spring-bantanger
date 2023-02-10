package com.bantanger.springframework.beans.factory.aware;

import com.bantanger.springframework.beans.exception.BeansException;
import com.bantanger.springframework.beans.factory.BeanFactory;

/**
 * 能感知到所属的 BeanFactory
 * @author BanTanger 半糖
 * @Date 2023/2/10 21:05
 */
public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;

}
