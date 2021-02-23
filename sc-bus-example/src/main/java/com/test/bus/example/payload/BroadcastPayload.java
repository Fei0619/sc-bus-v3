package com.test.bus.example.payload;

import com.test.bus.common.message.EventPayload;
import lombok.Getter;
import lombok.Setter;


/**
 * @author 费世程
 * @date 2021/2/22 17:10
 */
@Getter
@Setter
public class BroadcastPayload implements EventPayload {

  private static final String TOPIC = "broadcast_message";

  private String message;

  @Override
  public String getTopic() {
    // 可在此处对参数进行校验
    if (message == null || message.isEmpty()) {
      throw new IllegalArgumentException("广播消息不能为空！");
    }
    return TOPIC;
  }
}
