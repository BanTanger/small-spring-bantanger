package com.bantanger.springframework.beans.factory.config;

/**
 * Bean 引用
 * 标注非基本类型的对象类型
 * @author BanTanger 半糖
 * @Date 2023/2/7 11:41
 */
public class BeanReference {

    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }

}
