package com.bantanger.springframework.aop;

/**
 * 定义类匹配类
 * 用于切点找到给定的接口和目标类。
 * @author BanTanger 半糖
 * @Date 2023/2/13 20:36
 */
public interface ClassFilter {

    /**
     * 切入点是否应适用于给定的接口或目标类？
     * @param clazz
     * @return
     */
    boolean matches(Class<?> clazz);

}
