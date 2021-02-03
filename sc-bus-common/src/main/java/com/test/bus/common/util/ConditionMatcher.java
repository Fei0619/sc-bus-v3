package com.test.bus.common.util;

import com.test.bus.common.message.EventHeaders;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.util.*;

/**
 * @author 费世程
 * @date 2021/2/3 15:58
 */
public class ConditionMatcher {

  private static final Logger log = LoggerFactory.getLogger(ConditionMatcher.class);

  /**
   * 将条件表达式解析为条件组
   *
   * @param conditionExpression 事件订阅的条件表达式
   * @return 条件组
   * @author 费世程
   * @date 2021/2/3 16:06
   */
  public static List<Set<String>> parseConditionString(String conditionExpression) {
    if (StringUtils.isBlank(conditionExpression)) {
      return Collections.emptyList();
    }
    String[] group = StringUtils.split(conditionExpression, "|");
    List<Set<String>> conditionGroup = new ArrayList<>();
    for (String item : group) {
      String[] part = StringUtils.split(item, "&");
      Set<String> set = new HashSet<>(Arrays.asList(part));
      conditionGroup.add(set);
    }
    return conditionGroup;
  }

  /**
   * 条件匹配 </br>
   * 同组为且，异组为或
   *
   * @param conditionsGroup 条件组
   * @param eventHeaders    事件头
   * @return boolean
   * @author 费世程
   * @date 2021/2/3 19:08
   */
  public static boolean match(@Nonnull List<Set<String>> conditionsGroup, @Nonnull EventHeaders eventHeaders) {
    if (conditionsGroup.size() == 0) {
      return true;
    }
    if (eventHeaders.isEmpty()) {
      return false;
    }
    for (Set<String> condition : conditionsGroup) {
      if (matchConditions(condition, eventHeaders)) {
        return true;
      }
    }
    return false;
  }

  /**
   * 通过条件表达式判断是否符合
   *
   * @param conditionExpression 条件表达式
   * @param eventHeaders        事件头
   * @return boolean 是否符合条件
   * @author 费世程
   * @date 2021/2/3 19:42
   */
  public static boolean match(@Nonnull String conditionExpression, @Nonnull EventHeaders eventHeaders) {
    List<Set<String>> conditionsGroup = parseConditionString(conditionExpression);
    return match(conditionsGroup, eventHeaders);
  }

  private static boolean matchConditions(@Nonnull Set<String> conditions, @Nonnull EventHeaders eventHeaders) {
    if (conditions.size() == 0) {
      return true;
    }
    for (String condition : conditions) {
      char operator = '0';
      int index = -1;
      char[] chars = condition.toCharArray();
      for (int i = 0; i < chars.length; i++) {
        char c = chars[i];
        if (c == '>' || c == '<' || c == '=' || c == '^') {
          operator = c;
          index = i;
          break;
        }
      }
      if (index < 1) {
        log.info("条件判断不通过，缺少运算符,condition:{}", condition);
        return false;
      }
      String key = StringUtils.substring(condition, 0, index);
      Set<String> headValues = eventHeaders.get(key);
      if (headValues == null || headValues.size() == 0) {
        return false;
      }
      String value = StringUtils.substring(condition, index + 1);
      switch (operator) {
        case '>':
        case '<': {
          boolean flag = compare(headValues, value, operator, condition);
          if (!flag) {
            return false;
          }
          break;
        }
        case '=': {
          if (!headValues.contains(value)) {
            return false;
          }
          break;
        }
        case '^': {
          String[] split = value.split(",");
          boolean flag = false;
          for (String s : split) {
            if (headValues.contains(s)) {
              flag = true;
              break;
            }
          }
          if (!flag) {
            return false;
          }
          break;
        }
        default: {
          log.info("不支持的运算符：{}，condition：{}", operator, condition);
          return false;
        }
      }
    }
    return true;
  }

  private static boolean compare(Set<String> headValues, String value, char operator, String condition) {
    boolean flag = false;
    for (String headValue : headValues) {
      long target;
      long cond;
      try {
        target = Long.parseLong(headValue);
        cond = Long.parseLong(value);
      } catch (NumberFormatException exception) {
        log.info("条件判断不通过，parseLong exception，condition:{},header:{}", condition, headValue);
        return false;
      }
      switch (operator) {
        case '>': {
          if (target > cond) {
            flag = true;
          }
          break;
        }
        case '<': {
          if (target < cond) {
            flag = true;
          }
          break;
        }
        default:
          break;
      }
    }
    return flag;
  }

}
