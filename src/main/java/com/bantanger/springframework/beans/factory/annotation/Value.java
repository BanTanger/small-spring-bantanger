package com.bantanger.springframework.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * @author BanTanger 半糖
 * @Date 2023/2/16 13:42
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Value {

    /**
     * 使用如下：@Value("${systemProperties.myProp}")
     * @return
     */
    String value();

}
