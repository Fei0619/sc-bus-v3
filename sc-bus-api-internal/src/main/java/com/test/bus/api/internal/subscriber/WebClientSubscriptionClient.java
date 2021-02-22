package com.test.bus.api.internal.subscriber;

import com.test.bus.api.internal.conf.BusClientProperties;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author 费世程
 * @date 2021/2/5 9:26
 */
public class WebClientSubscriptionClient implements SubscribeClient {

  private final BusClientProperties clientProperties;
  private final WebClient.Builder webClientBuilder;

  public WebClientSubscriptionClient(BusClientProperties clientProperties,
                                     WebClient.Builder webClientBuilder) {
    this.clientProperties = clientProperties;
    this.webClientBuilder = webClientBuilder;
  }

}
