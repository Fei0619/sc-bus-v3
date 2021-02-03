package com.test.bus.common.message;

import javax.annotation.Nonnull;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 时间消息生成器
 *
 * @author 费世程
 * @date 2021/2/3 10:49
 */
@SuppressWarnings("unused")
public class EventHolder {

  private int cursor = -1;
  private final List<EventMessage<?>> messages = new ArrayList<>();

  @Nonnull
  public static EventHolder create() {
    return new EventHolder();
  }

  @Nonnull
  public static EventHolder create(@Nonnull String topic, @Nonnull Object payload) {
    final EventHolder eventHolder = new EventHolder();
    final EventMessage<Object> eventMessage = new EventMessage<>(topic, payload);
    eventHolder.messages.add(eventMessage);
    return eventHolder;
  }

  @Nonnull
  public static EventHolder create(@Nonnull EventPayload payload) {
    return create(payload.getTopic(), payload);
  }

  /**
   * 追加事件
   */
  @Nonnull
  public EventHolder then(@Nonnull String topic, @Nonnull Object payload) {
    final EventMessage<Object> eventMessage = new EventMessage<>(topic, payload);
    return then(eventMessage);
  }

  @Nonnull
  public EventHolder then(@Nonnull EventPayload payload) {
    return then(payload.getTopic(), payload);
  }

  @Nonnull
  public EventHolder then(@Nonnull EventMessage<?> eventMessage) {
    messages.add(eventMessage);
    cursor++;
    return this;
  }

  @Nonnull
  public EventHolder addHeader(@Nonnull String headName, @Nonnull String headValue) {
    EventMessage<?> eventMessage = messages.get(cursor);
    eventMessage.getHeaders().add(headName, headValue);
    return this;
  }

  @Nonnull
  public EventHolder addHeaders(@Nonnull String headName, @Nonnull Collection<String> headValues) {
    EventMessage<?> eventMessage = messages.get(cursor);
    eventMessage.getHeaders().addAll(headName, headValues);
    return this;
  }

  public EventHolder addHeaders(@Nonnull String headName, @Nonnull String... headValues) {
    EventMessage<?> eventMessage = messages.get(cursor);
    eventMessage.getHeaders().addAll(headName, Arrays.asList(headValues));
    return this;
  }

  /**
   * 设置延迟时间
   */
  @Nonnull
  public EventHolder delay(@Nonnull Duration duration) {
    EventMessage<?> eventMessage = messages.get(cursor);
    eventMessage.setDelayMillis(duration.toMillis());
    return this;
  }

  /**
   * 设置platform
   */
  @Nonnull
  public EventHolder setPlatform(@Nonnull String platform) {
    EventMessage<?> eventMessage = messages.get(cursor);
    eventMessage.setPlatform(platform);
    return this;
  }

  private EventHolder() {
  }

  @Nonnull
  public List<EventMessage<?>> getMessages() {
    return messages;
  }
}
