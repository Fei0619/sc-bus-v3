package com.test.bus.api.internal.subscriber.deliver;

import com.fasterxml.jackson.databind.JavaType;
import com.test.bus.api.subscriber.deliver.EventDeliver;
import com.test.bus.api.subscriber.deliver.EventListenerContext;
import com.test.bus.common.message.EventHeaders;
import com.test.bus.common.message.EventMessage;
import com.test.bus.common.util.ConditionMatcher;
import com.test.share.common.json.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * @author 费世程
 * @date 2021/2/3 14:27
 */
public class DeliverImpl implements EventDeliver {

  private final Logger log = LoggerFactory.getLogger(DeliverImpl.class);

  @Override
  public void deliverEvent(@Nonnull EventMessage<Object> eventMessage) {
    String topic = eventMessage.getTopic();
    List<EventListenerContext> contextList = ListenerFactory.getEventListenerContext(topic);
    if (contextList == null || contextList.size() == 0) {
      log.warn("No event listener found for this topic -> {}", topic);
      return;
    }
    EventHeaders headers = eventMessage.getHeaders();
    String messageString = JsonUtils.INSTANCE.toJsonString(eventMessage);
    for (EventListenerContext context : contextList) {
      if (!ConditionMatcher.match(context.getConditionsGroup(), headers)) {
        continue;
      }
      JavaType javaType = context.getJavaType();
      Object message;
      try {
        message = JsonUtils.INSTANCE.parseJson(messageString, javaType);
      } catch (Exception e) {
        log.warn("@BusEventListener(topic=\"{}\") json解析异常 -> message:{},exception:{}", messageString, e.getMessage());
        return;
      }
      try {
        context.process(message);
      } catch (Exception e) {
        log.warn("@BusEventListener(topic=\"{}\") process eventMessage出现异常 -> message:{},exception:{}", messageString, e.getMessage());
      }
    }
  }

}
