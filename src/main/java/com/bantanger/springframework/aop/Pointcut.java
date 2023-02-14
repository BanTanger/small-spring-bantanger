package com.bantanger.springframework.aop;


/**
 * 切入点接口
 * 定义用于获取 ClassFilter、MethodMatcher 的两个类，这两个接口获取都是切点表达式提供的内容。
 * @author BanTanger 半糖
 * @Date 2023/2/13 20:35
 */
public interface Pointcut {

    /**
     * 返回此切入点的类筛选器。
     * @return
     */
    ClassFilter getClassFilter();

    /**
     * 返回此切入点的方法匹配器
     * @return
     */
    MethodMatcher getMethodMatcher();

}
