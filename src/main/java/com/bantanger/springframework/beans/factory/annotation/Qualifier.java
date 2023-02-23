package com.bantanger.springframework.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * 此注释可以在字段或参数上使用，作为自动连线时候选 bean 的限定符。它还可用于批注其他自定义批注，然后这些批注又可用作限定符。
 * @author BanTanger 半糖
 * @Date 2023/2/16 13:40
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Qualifier {

    String value() default "";

}
