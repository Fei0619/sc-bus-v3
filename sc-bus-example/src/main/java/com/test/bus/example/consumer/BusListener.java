package com.test.bus.example.consumer;

import com.test.bus.api.subscriber.annotation.BusEventListener;
import com.test.bus.api.subscriber.annotation.BusListenerBean;
import com.test.bus.common.message.EventMessage;
import com.test.bus.example.payload.BroadcastPayload;
import com.test.share.common.json.JsonUtils;
import org.springframework.stereotype.Component;

/**
 * @author 费世程
 * @date 2021/2/22 16:57
 */
@Component
@BusListenerBean
public class BusListener {

  @BusEventListener(topic = "broadcast_message")
  public void eventListener(EventMessage<BroadcastPayload> eventMessage) {
    System.err.println(JsonUtils.INSTANCE.toJsonString(eventMessage));
  }

}
