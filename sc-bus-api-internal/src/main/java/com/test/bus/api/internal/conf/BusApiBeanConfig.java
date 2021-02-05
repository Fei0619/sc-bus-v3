package com.test.bus.api.internal.conf;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author 费世程
 * @date 2021/2/4 10:24
 */
@Configuration
@EnableConfigurationProperties(value = BusClientProperties.class)
public class BusApiBeanConfig {

//  public WebClient.Builder loadBalancedWebClientBuilder(){
//  }

}
