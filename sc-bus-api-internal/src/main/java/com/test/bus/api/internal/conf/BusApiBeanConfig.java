package com.test.bus.api.internal.conf;

import com.test.bus.api.internal.publisher.InternalEventPublisher;
import com.test.bus.api.internal.subscriber.SubscribeClient;
import com.test.bus.api.internal.subscriber.WebClientSubscriptionClient;
import com.test.bus.api.internal.subscriber.deliver.DeliverImpl;
import com.test.bus.api.internal.subscriber.deliver.ListenerFactory;
import com.test.bus.api.internal.subscriber.receiver.BusEventReceiveController;
import com.test.bus.api.publisher.EventPublisher;
import com.test.share.common.http.WebClients;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author 费世程
 * @date 2021/2/4 10:24
 */
@Configuration
@EnableConfigurationProperties(value = BusClientProperties.class)
public class BusApiBeanConfig {

  @Bean
  @LoadBalanced
  @ConditionalOnMissingBean
  public WebClient.Builder loadBalancedWebClientBuilder() {
    return WebClients.createWebClientBuilder(1000, 1000L, 1000L);
  }

  @Bean
  public DeliverImpl deliver() {
    return new DeliverImpl();
  }

  @Bean
  public SubscribeClient subscribeClient(BusClientProperties properties,
                                         WebClient.Builder webClientBuilder) {
    return new WebClientSubscriptionClient(properties, webClientBuilder);
  }

  @Bean
  public ListenerFactory listenerFactory(ApplicationContext context) {
    return new ListenerFactory(context);
  }

  @Bean
  @ConditionalOnMissingBean
  public EventPublisher eventPublisher(BusEventReceiveController eventReceiveController) {
    return new InternalEventPublisher(eventReceiveController);
  }

}
