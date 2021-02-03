package com.test.bus.api.subscriber.deliver;

import com.fasterxml.jackson.databind.JavaType;

import javax.annotation.Nonnull;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

/**
 * @author 费世程
 * @date 2021/2/3 14:45
 */
public class EventListenerContext {

  private final Object instance;
  private final Method method;
  private final JavaType javaType;
  private final List<Set<String>> conditionsGroup;

  public EventListenerContext(Object instance,
                              Method method,
                              JavaType javaType,
                              List<Set<String>> conditionsGroup) {
    this.instance = instance;
    this.method = method;
    this.javaType = javaType;
    this.conditionsGroup = conditionsGroup;
  }

  public Object process(@Nonnull Object message) throws Exception {
    return this.method.invoke(this.instance, message);
  }

  public Object getInstance() {
    return instance;
  }

  public Method getMethod() {
    return method;
  }

  public JavaType getJavaType() {
    return javaType;
  }

  public List<Set<String>> getConditionsGroup() {
    return conditionsGroup;
  }
}
