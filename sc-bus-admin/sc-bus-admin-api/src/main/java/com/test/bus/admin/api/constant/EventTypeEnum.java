package com.test.bus.admin.api.constant;

/**
 * @author 费世程
 * @date 2021/2/24 10:04
 */
public enum EventTypeEnum {
  /**
   * 发布
   */
  PUBLISH(0),
  /**
   * 订阅
   */
  SUBSCRIBE(1),
  ;
  private final int code;

  EventTypeEnum(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }}
