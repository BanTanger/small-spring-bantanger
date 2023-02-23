package com.bantanger.springframework.context.annotation;

import java.lang.annotation.*;

/**
 * 作用范围自定义注解
 * 方便通过配置 Bean 对象注解的时候，拿到 Bean 对象的作用域。不过一般都使用默认的 singleton
 * @author BanTanger 半糖
 * @Date 2023/2/15 16:54
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Scope {

    String value() default "singleton";

}
