package com.test.bus.api.internal.conf;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 费世程
 * @date 2021/2/4 10:14
 */
@Getter
@Setter
public class SubscriptionProperties {

  /**
   * 订阅主题
   */
  private String topic;
  /**
   * 订阅条件 </br>
   * 同组为且，异组为或 </br>
   * example: tenantId=1&deviceGroupId^123|tenantId=9&deviceId=1001 </br>
   * -> where (tenantId=1 and deviceGroupId in (1,2,3)) or (tenantId=9 and deviceId=1001) </br>
   */
  private String condition;
  /**
   * 是否以广播的方式订阅
   */
  private boolean broadcast;
  /**
   * 接收推动方式：LoadBalance/Host
   */
  private String pushType = "LoadBalance";
  /**
   * 接收推送的地址
   */
  private String receiveUrl;

}
