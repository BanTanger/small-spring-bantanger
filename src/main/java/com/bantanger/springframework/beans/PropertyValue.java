package com.bantanger.springframework.beans;

/**
 * Bean 属性信息
 * @author BanTanger 半糖
 * @Date 2023/2/7 11:27
 */
public class PropertyValue {

    private final String name;

    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

}