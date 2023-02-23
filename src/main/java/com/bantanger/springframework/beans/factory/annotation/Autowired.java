package com.bantanger.springframework.beans.factory.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 将构造函数、字段、setter 方法或配置方法标记为由 Spring 的依赖注入工具自动连接。
 * @author BanTanger 半糖
 * @Date 2023/2/16 13:39
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR})
public @interface Autowired {
}
