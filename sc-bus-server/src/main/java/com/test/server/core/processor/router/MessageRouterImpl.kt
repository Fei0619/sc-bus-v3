package com.test.server.core.processor.router

import com.test.bus.common.message.EventMessage
import com.test.server.core.pojo.EventPushInfo
import reactor.core.publisher.Flux

/**
 * @author 费世程
 * @date 2021/2/23 16:25
 */
class MessageRouterImpl : MessageRouter {

  override fun routing(messages: Flux<EventMessage<Any>>): Flux<EventPushInfo> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

}
