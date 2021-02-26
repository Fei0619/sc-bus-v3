package com.test.bus.api.internal.conf;

import com.test.bus.api.internal.subscriber.SubscribeClient;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author 费世程
 * @date 2021/2/24 14:34
 */
@Component
public class BusApiRunner implements ApplicationRunner {

  private final SubscribeClient subscribeClient;

  public BusApiRunner(SubscribeClient subscribeClient) {
    this.subscribeClient = subscribeClient;
  }

  @Override
  public void run(ApplicationArguments args) {
    subscribeClient.autoSubscribe();
  }

}
