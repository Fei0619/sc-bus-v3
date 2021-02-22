package com.test.bus.common.constant;

/**
 * @author 费世程
 * @date 2021/2/5 11:04
 */
public enum PushType {

  /**
   * LoadBalance: </br>
   * 服务和事件总线在同一注册中心上，通过负载均衡进行调用 </br>
   * - http://sc-bus/...
   */
  LoadBalance(0),
  /**
   * full http url </br>
   * 服务事件总线不在同一注册中心上，此时通过完整的地址进行调用
   */
  Host(1),
  ;
  private final int code;

  PushType(Integer code) {
    this.code = code;
  }

  public int code() {
    return this.code;
  }

  public static PushType valueOfCode(int code) {
    switch (code) {
      case 0:
        return PushType.LoadBalance;
      case 1:
        return PushType.Host;
      default:
        throw new RuntimeException("不合法的PushType Code -> " + code);
    }
  }

}
