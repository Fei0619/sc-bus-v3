package com.test.server.service

import com.test.server.core.pojo.SubscriptionDetails

/**
 * @author 费世程
 * @date 2021/2/23 16:27
 */
interface BusCache {

  fun getTopicSubscriptions(topic: String): List<SubscriptionDetails>?

}
