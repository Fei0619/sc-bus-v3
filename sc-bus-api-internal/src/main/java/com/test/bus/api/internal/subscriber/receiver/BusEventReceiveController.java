package com.test.bus.api.internal.subscriber.receiver;

import com.test.bus.api.internal.constant.BusUrl;
import com.test.bus.api.subscriber.deliver.EventDeliver;
import com.test.bus.api.subscriber.receiver.EventReceiver;
import com.test.bus.common.message.EventMessage;
import com.test.share.common.result.Res;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Nonnull;

/**
 * @author 费世程
 * @date 2021/2/3 14:37
 */
@RestController
public class BusEventReceiveController implements EventReceiver {

  private final EventDeliver eventDeliver;

  public BusEventReceiveController(EventDeliver eventDeliver) {
    this.eventDeliver = eventDeliver;
  }

  @PostMapping(value = BusUrl.CLIENT_EVENT_RECEIVE_URL)
  @Override
  public Res<Object> receiveEventMessage(@RequestBody @Nonnull EventMessage<Object> eventMessage) {
    eventDeliver.deliverEvent(eventMessage);
    return Res.success();
  }

}
