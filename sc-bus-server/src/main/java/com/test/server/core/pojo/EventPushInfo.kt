package com.test.server.core.pojo

import com.test.bus.common.message.EventMessage

/**
 * @author 费世程
 * @date 2021/2/5 10:58
 */
class EventPushInfo(val eventMessage: EventMessage<Any>,
                    val subscriptionDetails: SubscriptionDetails) {

  /**
   * 实际推送类型
   */
  val realPushType = subscriptionDetails.pushType
  /**
   * 实际推送地址
   */
  val realReceiveUrl = subscriptionDetails.receiveUrl
  /**
   * 延迟推送时间
   */
  val delayMillis = -1L
  /**
   * 当前推送次数
   */
  val currentPush = 0

}
