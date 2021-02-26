package com.test.bus.admin.api.transfer.rsp;

import lombok.Getter;
import lombok.Setter;
import reactor.util.annotation.NonNull;
import reactor.util.annotation.Nullable;

import java.time.LocalDateTime;

/**
 * @author 费世程
 * @date 2021/2/24 10:00
 */
@Getter
@Setter
public class EventRsp {

  /**
   * 主键
   */
  private long eventId;
  /**
   * 归属模块id
   */
  private long moduleId;
  /**
   * 归属模块名称
   */
  private String moduleName;
  /**
   * 事件类型 -> {@link com.test.bus.admin.api.constant.EventTypeEnum}
   */
  private int eventType;
  /**
   * 主题
   */
  private String topic;
  /**
   * 事件名称
   */
  private String eventName;
  /**
   * 描述
   */
  @Nullable
  private String eventDesc;
  /**
   * 创建时间
   */
  @NonNull
  private LocalDateTime createTime;
  /**
   * 是否已被订阅者订阅
   */
  @Nullable
  private Boolean subscribed;


}
