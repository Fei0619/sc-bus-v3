package com.test.server.pojo

import com.test.server.core.pojo.SubscriptionDetails

/**
 * @author 费世程
 * @date 2021/2/23 17:33
 */
class LocalCache {

  // topic -> subscriptions
  lateinit var eventSubscriptionMapper: Map<String, List<SubscriptionDetails>>

}
