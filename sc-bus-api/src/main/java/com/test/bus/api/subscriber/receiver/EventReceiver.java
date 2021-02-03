package com.test.bus.api.subscriber.receiver;

import com.test.bus.common.message.EventMessage;
import com.test.share.common.result.Res;

/**
 * 接收服务事件总线推送消息的服务端接口 </br>
 * 此接口用于为事件订阅者服务的controller提供接口规范
 *
 * @author 费世程
 * @date 2021/2/3 14:32
 */
public interface EventReceiver {

  /**
   * 接收event-bus推送消息的接口
   */
  Res<Object> receiveEventMessage(EventMessage<Object> eventMessage);

}
