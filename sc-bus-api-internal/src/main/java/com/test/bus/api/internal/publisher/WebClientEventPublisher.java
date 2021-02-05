package com.test.bus.api.internal.publisher;

import com.test.bus.api.publisher.EventPublisher;
import com.test.bus.common.message.EventMessage;
import com.test.bus.common.result.PublishResult;
import com.test.share.common.result.Res;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author 费世程
 * @date 2021/2/4 9:46
 */
public class WebClientEventPublisher implements EventPublisher {

  private final Logger log = LoggerFactory.getLogger(WebClientEventPublisher.class);

  @Override
  public Res<PublishResult> publish(EventMessage<?> message) {
    return null;
  }

  @Override
  public Res<PublishResult> batchPublish(List<EventMessage<?>> messages) {
    return null;
  }
}
