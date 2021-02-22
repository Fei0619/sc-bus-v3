package com.test.server.core.pojo

import com.test.bus.common.constant.PushType

/**
 * @author 费世程
 * @date 2021/2/5 11:00
 */
class SubscriptionDetails {

  /**
   * 订阅者id
   */
  var subscriberId: String = ""
  /**
   * 接收推送的地址
   */
  var receiveUrl: String = ""
  /**
   * 订阅者分类 </br>
   * 0：内部订阅者 </br>
   * 1：北向平台订阅者（只能接收到属于自己platform的消息） </br>
   */
  var subscriberType: Int = 0
  /**
   * 所属平台
   */
  var platform: String = ""
  /**
   * 地址类型
   */
  var pushType: PushType = PushType.LoadBalance
  /**
   * 订阅主题
   */
  var topic: String = ""
  /**
   * 订阅条件
   */
  var condition: String = ""
  /**
   * 是否广播
   */
  var broadcast: Boolean = false

  private var conditionSets: List<Set<String>>? = null

}
