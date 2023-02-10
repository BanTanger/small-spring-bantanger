package com.bantanger.springframework.beans.factory.support.manage;

/**
 * @author BanTanger 半糖
 * @Date 2023/2/9 22:03
 */
public interface DisposableBean {

    void destroy() throws Exception;

}
