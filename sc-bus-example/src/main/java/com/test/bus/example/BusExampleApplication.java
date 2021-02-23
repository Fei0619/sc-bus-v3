package com.test.bus.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 费世程
 * @date 2021/2/22 16:55
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.test")
public class BusExampleApplication {

  public static void main(String[] args) {
    SpringApplication.run(BusExampleApplication.class, args);
  }

}
