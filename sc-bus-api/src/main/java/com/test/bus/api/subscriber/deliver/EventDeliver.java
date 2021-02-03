package com.test.bus.api.subscriber.deliver;

import com.test.bus.common.message.EventMessage;

import javax.annotation.Nonnull;

/**
 * @author 费世程
 * @date 2021/2/3 14:22
 */
public interface EventDeliver {

  void deliverEvent(@Nonnull EventMessage<Object> eventMessage);

}
