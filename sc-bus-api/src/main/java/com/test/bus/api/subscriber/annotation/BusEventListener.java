package com.test.bus.api.subscriber.annotation;

import java.lang.annotation.*;

/**
 * @author 费世程
 * @date 2021/2/3 14:54
 */
@Documented
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface BusEventListener {

  /**
   * 监听的主题
   */
  String topic();

  /**
   * 监听条件
   */
  String condition() default "";

}
