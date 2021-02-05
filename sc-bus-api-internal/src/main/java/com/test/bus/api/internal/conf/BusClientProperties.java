package com.test.bus.api.internal.conf;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 费世程
 * @date 2021/2/4 10:15
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "sc.bus.client")
public class BusClientProperties {

  /**
   * 是否开启自动注册
   */
  private boolean autoSubscribe = false;
  /**
   * event-bus服务端地址
   */
  private String baseUri = "http://BUS-SERVER";
  /**
   * 服务id
   */
  private String subscriberId;
  /**
   * 订阅关系
   */
  private List<SubscriptionProperties> subscriptions = new ArrayList<>();

}
