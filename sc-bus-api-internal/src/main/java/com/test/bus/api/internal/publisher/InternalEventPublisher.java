package com.test.bus.api.internal.publisher;

import com.test.bus.api.publisher.EventPublisher;
import com.test.bus.common.message.EventMessage;

import java.util.List;

/**
 * @author 费世程
 * @date 2021/2/3 14:30
 */
public class InternalEventPublisher implements EventPublisher {

  @Override
  public Object publish(EventMessage message) {
    return null;
  }

  @Override
  public Object batchPublish(List list) {
    return null;
  }

}
