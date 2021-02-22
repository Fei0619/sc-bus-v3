package com.test.server.core.processor

import com.test.bus.common.message.EventMessage
import com.test.bus.common.result.PublishResult
import com.test.share.common.result.Res
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * @author 费世程
 * @date 2021/2/5 10:10
 */
interface MessageReceiver {

  fun receiveMessages(messages: Flux<EventMessage<Any>>): Mono<Res<PublishResult>>

}
