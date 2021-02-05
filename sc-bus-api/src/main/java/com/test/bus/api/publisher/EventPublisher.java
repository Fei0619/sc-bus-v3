package com.test.bus.api.publisher;

import com.test.bus.common.result.PublishResult;
import com.test.share.common.result.Res;

/**
 * @author 费世程
 * @date 2021/2/3 10:20
 */
public interface EventPublisher extends Publisher<Res<PublishResult>> {
}
