package com.test.bus.api.internal.subscriber.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 费世程
 * @date 2021/2/24 15:10
 */
@Getter
@Setter
public class SubscribeReq {

  private String topic;
  private String condition;
  private boolean broadcast;

}
