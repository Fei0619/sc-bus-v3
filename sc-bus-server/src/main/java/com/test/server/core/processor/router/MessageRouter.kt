package com.test.server.core.processor.router

import com.test.bus.common.message.EventMessage
import com.test.server.core.pojo.EventPushInfo
import reactor.core.publisher.Flux

/**
 * @author 费世程
 * @date 2021/2/5 10:57
 */
interface MessageRouter {

  fun routing(messages: Flux<EventMessage<Any>>): Flux<EventPushInfo>

}
