package com.bantanger.springframework.beans.factory.aware;

/**
 * 能感知到所属的 BeanName
 * @author BanTanger 半糖
 * @Date 2023/2/10 21:06
 */
public interface BeanNameAware extends Aware {

    void setBeanName(String name);

}
