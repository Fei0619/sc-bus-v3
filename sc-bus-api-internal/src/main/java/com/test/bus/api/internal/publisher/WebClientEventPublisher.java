package com.test.bus.api.internal.publisher;

import com.fasterxml.jackson.core.type.TypeReference;
import com.test.bus.api.internal.conf.BusClientProperties;
import com.test.bus.api.publisher.EventPublisher;
import com.test.bus.common.message.EventMessage;
import com.test.bus.common.result.PublishResult;
import com.test.share.common.json.JsonUtils;
import com.test.share.common.result.Res;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

/**
 * @author 费世程
 * @date 2021/2/4 9:46
 */
public class WebClientEventPublisher implements EventPublisher {

  private final Logger log = LoggerFactory.getLogger(WebClientEventPublisher.class);
  private final TypeReference<Res<PublishResult>> TYPE_REFERENCE = new TypeReference<Res<PublishResult>>() {
  };
  private final BusClientProperties properties;
  private final WebClient.Builder webClientBuilder;

  public WebClientEventPublisher(BusClientProperties properties, WebClient.Builder webClientBuilder) {
    this.properties = properties;
    this.webClientBuilder = webClientBuilder;
  }

  @Override
  public Res<PublishResult> publish(EventMessage<?> message) {
    return doPush(message, "/publish/one");
  }

  @Override
  public Res<PublishResult> batchPublish(List<EventMessage<?>> messages) {
    return doPush(messages, "/publish/batch");
  }

  private <T> Res<PublishResult> doPush(T message, String uri) {
    final String requestUrl = properties.getBaseUri() + uri;
    final String resultString = webClientBuilder.build().post().uri(requestUrl).body(BodyInserters.fromValue(message))
        .retrieve().bodyToMono(String.class).block();
    if (resultString == null) {
      return Res.error("返回数据为空！");
    }
    return JsonUtils.INSTANCE.parseJson(resultString, TYPE_REFERENCE);
  }

}
