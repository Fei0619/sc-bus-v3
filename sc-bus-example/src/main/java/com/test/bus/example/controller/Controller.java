package com.test.bus.example.controller;

import com.test.bus.api.publisher.EventPublisher;
import com.test.bus.common.message.EventHolder;
import com.test.bus.example.payload.BroadcastPayload;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 费世程
 * @date 2021/2/22 17:19
 */
@RestController
public class Controller {

  private final EventPublisher eventPublisher;

  public Controller(EventPublisher eventPublisher) {
    this.eventPublisher = eventPublisher;
  }

  @GetMapping(value = "/broadcast")
  public void broadcastMessage() {
    BroadcastPayload payload = new BroadcastPayload();
    payload.setMessage("来自[sc-bus]的广播消息..");
    EventHolder holder = EventHolder.create(payload);
    eventPublisher.publish(holder);
  }

}
