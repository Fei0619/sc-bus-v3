package com.test.bus.common.message;


import lombok.*;

import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * @author 费世程
 * @date 2021/2/3 10:25
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventMessage<T> {

  /**
   * 事件id
   */
  private String eventId = UUID.randomUUID().toString();
  /**
   * platform
   */
  private String platform;
  /**
   * 事件主题
   */
  private String topic;
  /**
   * 消息头，可用于条件匹配
   */
  private EventHeaders headers = EventHeaders.create();
  /**
   * 延时时间，默认不延时
   */
  private long delayMillis = -1L;
  /**
   * 消息体
   */
  private T payload;
  /**
   * 事件产生的时间戳
   */
  private long timestamp = System.currentTimeMillis();

  public EventMessage(@Nonnull String topic, @Nonnull T payload) {
    this.topic = topic;
    this.payload = payload;
  }

}
