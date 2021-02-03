package com.test.bus.api.publisher;


import com.test.bus.common.message.EventHolder;
import com.test.bus.common.message.EventMessage;

import java.util.List;

/**
 * @author 费世程
 * @date 2021/2/3 10:19
 */
@SuppressWarnings("unused")
public interface Publisher<T> {

  /**
   * 发布一条消息
   *
   * @param message 消息
   * @return T
   * @author 费世程
   * @date 2021/2/3 10:47
   */
  T publish(EventMessage<?> message);

  /**
   * 批量发布消息
   *
   * @param messages 消息列表
   * @return T
   * @author 费世程
   * @date 2021/2/3 10:48
   */
  T batchPublish(List<EventMessage<?>> messages);

  default T publish(EventHolder eventHolder) {
    List<EventMessage<?>> messages = eventHolder.getMessages();
    if (messages.size() == 1) {
      return publish(messages.get(0));
    } else {
      return batchPublish(messages);
    }
  }

}
