package com.test.bus.api.internal.subscriber.deliver;

import com.fasterxml.jackson.databind.JavaType;
import com.test.bus.api.subscriber.annotation.BusEventListener;
import com.test.bus.api.subscriber.annotation.BusListenerBean;
import com.test.bus.api.subscriber.deliver.EventListenerContext;
import com.test.bus.common.util.ConditionMatcher;
import com.test.share.common.json.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 费世程
 * @date 2021/2/3 14:51
 */
public class ListenerFactory implements InitializingBean {

  private final ApplicationContext applicationContext;
  private final Logger log = LoggerFactory.getLogger(ListenerFactory.class);
  /**
   * topic -> EventListenerContext
   */
  private static final Map<String, List<EventListenerContext>> EVENT_HANDLER_MAPPING = new ConcurrentHashMap<>();

  public ListenerFactory(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  /**
   * 获取监听某事件主题的所有方法信息
   *
   * @param topic 事件主题
   * @return List<EventListenerContext>
   */
  public static List<EventListenerContext> getEventListenerContext(String topic) {
    return EVENT_HANDLER_MAPPING.get(topic);
  }

  /**
   * 初始化[EVENT_HANDLER_MAPPING]
   */
  @Override
  public void afterPropertiesSet() {
    // 获取所有被[@BusListenerBean]注解注释的类
    Map<String, Object> beanMapping = applicationContext.getBeansWithAnnotation(BusListenerBean.class);
    beanMapping.values().forEach(bean -> {
      Class<?> clazz = bean.getClass();
      for (Method method : clazz.getMethods()) {
        BusEventListener eventListener = method.getAnnotation(BusEventListener.class);
        if (eventListener != null) {
          String topic = eventListener.topic();
          if (StringUtils.isBlank(topic)) {
            log.error("{}#{} @BusEventListener未指定topic..", clazz.getName(), method.getName());
            continue;
          }
          Parameter[] parameters = method.getParameters();
          if (parameters.length == 1) {
            Parameter parameter = parameters[0];
            Type type = parameter.getType();
            JavaType javaType = JsonUtils.INSTANCE.getJavaType(type);
            List<EventListenerContext> contextList = EVENT_HANDLER_MAPPING.computeIfAbsent(topic, (key) -> new ArrayList<>());
            List<Set<String>> conditions = ConditionMatcher.parseConditionString(eventListener.condition());
            contextList.add(new EventListenerContext(bean, method, javaType, conditions));
          } else {
            log.error("{}#{} 参数列表过长...", clazz.getName(), method.getName());
          }
        }
      }
    });

  }

}
