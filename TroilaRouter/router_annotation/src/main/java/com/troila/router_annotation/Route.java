package com.troila.router_annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
//注解保存到.class
@Retention(RetentionPolicy.CLASS)
public @interface Route {
    String value();   //必须传递字符串
}
