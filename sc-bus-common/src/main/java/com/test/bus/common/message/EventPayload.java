package com.test.bus.common.message;

import java.beans.Transient;

/**
 * 消息内容的bean对象，方便对事件主题及消息结构体进行管理
 *
 * @author 费世程
 * @date 2021/2/3 11:11
 */
public interface EventPayload {

  /**
   * 获取事件主题 </br>
   * [@Transient]注解：序列化对象的时候该属性不会序列化到指定的目的地中
   *
   * @return 事件主题
   */
  @Transient
  String getTopic();

}
