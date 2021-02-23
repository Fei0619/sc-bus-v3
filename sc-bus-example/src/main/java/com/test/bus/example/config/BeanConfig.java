package com.test.bus.example.config;

import com.test.bus.api.internal.conf.BusClientProperties;
import com.test.bus.api.internal.publisher.WebClientEventPublisher;
import com.test.bus.api.publisher.EventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author 宋志宗
 * @date 2020/7/24
 */
@Configuration
public class BeanConfig {
//  @Bean
//  public EventPublisher eventPublisher(WebClient.Builder loadBalancedWebClientBuilder,
//                                       BusClientProperties busClientProperties) {
//    return new WebClientEventPublisher(busClientProperties, loadBalancedWebClientBuilder);
//  }
}
