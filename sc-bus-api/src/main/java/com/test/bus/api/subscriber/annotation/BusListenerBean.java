package com.test.bus.api.subscriber.annotation;

import java.lang.annotation.*;

/**
 * @author 费世程
 * @date 2021/2/3 14:54
 */
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
public @interface BusListenerBean {
}
