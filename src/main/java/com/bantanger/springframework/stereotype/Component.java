package com.bantanger.springframework.stereotype;

import java.lang.annotation.*;

/**
 * 标注一个类为 Spring 容器的 Bean，（把普通 pojo 实例化到 spring 容器中，相当于配置文件中的 bean 标签）
 * 除此之外还有 Service、Controller，不过所有的处理方式基本一致，这里就只展示一个 Component 即可
 * @author BanTanger 半糖
 * @Date 2023/2/15 16:55
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {

    String value() default "";

}
