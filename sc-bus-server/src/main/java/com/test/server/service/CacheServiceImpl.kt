package com.test.server.service

import com.test.server.core.pojo.SubscriptionDetails
import com.test.server.pojo.LocalCache
import com.test.share.common.intf.Destroyable
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InitializingBean
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

/**
 * @author 费世程
 * @date 2021/2/23 16:29
 */
@Service
class CacheServiceImpl : BusCache, InitializingBean, Destroyable {

  val log: Logger = LoggerFactory.getLogger(CacheServiceImpl::class.java)

  private val currentLocalCache = LocalCache()

  fun refreshCache() {

  }

  /**
   * 每十分钟刷新一次本地缓存
   */
  @Scheduled(initialDelay = 10 * 60 * 1000, fixedDelay = 10 * 60 * 1000)
  fun refreshLocalCacheScheduled() {
  }


  override fun afterPropertiesSet() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun destroy() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  /**
   * 获取[topic]的订阅关系列表
   */
  override fun getTopicSubscriptions(topic: String): List<SubscriptionDetails>? {
    return currentLocalCache.eventSubscriptionMapper[topic]
  }

}
