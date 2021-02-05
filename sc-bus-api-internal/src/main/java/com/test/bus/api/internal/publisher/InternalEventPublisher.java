package com.test.bus.api.internal.publisher;

import com.test.bus.api.internal.subscriber.receiver.BusEventReceiveController;
import com.test.bus.api.publisher.EventPublisher;
import com.test.bus.common.message.EventMessage;
import com.test.bus.common.result.PublishResult;
import com.test.share.common.json.JsonUtils;
import com.test.share.common.result.Res;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * @author 费世程
 * @date 2021/2/3 14:30
 */
public class InternalEventPublisher implements EventPublisher {

  private final BusEventReceiveController eventReceiveController;

  public InternalEventPublisher(@Nonnull BusEventReceiveController eventReceiveController) {
    this.eventReceiveController = eventReceiveController;
  }

  @Override
  public Res<PublishResult> publish(@Nonnull EventMessage<?> message) {
    String jsonString = JsonUtils.INSTANCE.toJsonString(message);
    EventMessage<Object> eventMessage = JsonUtils.INSTANCE.parseJson(jsonString, EventMessage.class, Object.class);
    eventReceiveController.receiveEventMessage(eventMessage);
    return Res.success();
  }

  @Override
  public Res<PublishResult> batchPublish(List<EventMessage<?>> messages) {
    messages.forEach(message -> {
      String jsonString = JsonUtils.INSTANCE.toJsonString(message);
      EventMessage<Object> eventMessage = JsonUtils.INSTANCE.parseJson(jsonString, EventMessage.class, Object.class);
      eventReceiveController.receiveEventMessage(eventMessage);
    });
    return Res.success();
  }
}
