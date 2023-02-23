package com.bantanger.springframework.beans.factory;

import com.bantanger.springframework.beans.exception.BeansException;

/**
 * @author BanTanger 半糖
 * @Date 2023/2/18 19:03
 */
public interface ObjectFactory<T> {

    T getObject() throws BeansException;

}
