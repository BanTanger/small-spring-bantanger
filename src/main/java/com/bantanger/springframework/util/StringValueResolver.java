package com.bantanger.springframework.util;

/**
 * @author BanTanger 半糖
 * @Date 2023/2/16 13:00
 */
public interface StringValueResolver {

    /**
     * 解析字符串接口
     * @param strVal
     * @return
     */
    String resolveStringValue(String strVal);

}
