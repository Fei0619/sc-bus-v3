package com.test.server.controller

import com.test.bus.common.message.EventMessage
import com.test.bus.common.result.PublishResult
import com.test.server.core.processor.MessageReceiver
import com.test.share.common.json.toJsonString
import com.test.share.common.result.Res
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux

/**
 * @author 费世程
 * @date 2021/2/5 9:49
 */
@RequestMapping("/publish")
@RestController
class PublishController(private val messageReceiver: MessageReceiver) {

  private val log = LoggerFactory.getLogger(PublishController::class.java)

  @PostMapping("/one")
  fun publish(message: EventMessage<Any>): Mono<Res<PublishResult>> {
    val startTimestamp = System.currentTimeMillis()
    if (log.isDebugEnabled) {
      log.debug("publish:{${message.toJsonString()}}")
    }
    return messageReceiver.receiveMessages(listOf(message).toFlux()).doFinally {
      val disposeMillis = System.currentTimeMillis() - startTimestamp
      log.info("publish..eventId={},disposeMillis={}ms", message.eventId, disposeMillis)
    }
  }

  @PostMapping("/batch")
  fun batchPublish(messages: List<EventMessage<Any>>): Mono<Res<PublishResult>> {
    val startTimestamp = System.currentTimeMillis()
    if (log.isDebugEnabled) {
      log.debug("publish:{${messages.toJsonString()}}")
    }
    return messageReceiver.receiveMessages(messages.toFlux()).doFinally {
      val disposeMillis = System.currentTimeMillis() - startTimestamp
      val eventIds = messages.joinToString { message -> message.eventId }
      log.info("publish..eventIds={},disposeMillis={}ms", eventIds, disposeMillis)
    }
  }

}
