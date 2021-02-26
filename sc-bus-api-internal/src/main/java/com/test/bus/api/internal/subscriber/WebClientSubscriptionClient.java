package com.test.bus.api.internal.subscriber;

import com.test.bus.api.internal.conf.BusClientProperties;
import com.test.bus.api.internal.conf.SubscriptionProperties;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

/**
 * @author 费世程
 * @date 2021/2/5 9:26
 */
public class WebClientSubscriptionClient implements SubscribeClient {

  private final Logger log = LoggerFactory.getLogger(WebClientSubscriptionClient.class);
  private final BusClientProperties clientProperties;
  private final WebClient.Builder webClientBuilder;

  public WebClientSubscriptionClient(BusClientProperties clientProperties,
                                     WebClient.Builder webClientBuilder) {
    this.clientProperties = clientProperties;
    this.webClientBuilder = webClientBuilder;
  }

  @Override
  public void autoSubscribe() {
    if (!clientProperties.isAutoSubscribe()) {
      log.debug("Auto Subscribe Is Off...");
      return;
    }
    String subscriberId = clientProperties.getSubscriberId();
    List<SubscriptionProperties> subscriptions = clientProperties.getSubscriptions();
    if (StringUtils.isBlank(subscriberId) || subscriptions.isEmpty()) {
      log.warn("自动订阅失败：subscriberId或subscriptions为空！");
      return;
    }


  }
}
