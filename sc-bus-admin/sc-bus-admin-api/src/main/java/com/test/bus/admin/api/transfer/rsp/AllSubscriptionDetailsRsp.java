package com.test.bus.admin.api.transfer.rsp;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author 费世程
 * @date 2021/2/24 10:00
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AllSubscriptionDetailsRsp {

  /**
   * 所有被订阅的事件列表
   */
  @NotNull
  private List<EventRsp> eventList;

}
