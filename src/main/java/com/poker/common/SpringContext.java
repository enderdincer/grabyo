package com.poker.common;

import com.poker.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * A util class to provide access to the spring context for the
 * classes that are not in the spring context.
 *
 * @author enderdincer
 */
public final class SpringContext {

  private SpringContext() {}

  // can be lazy init if moved to getBean with a null check
  private static final ApplicationContext APPLICATION_CONTEXT =
      new AnnotationConfigApplicationContext(AppConfig.class);

  /**
   * @param clazz class type of the desired spring bean
   * @param <T> type of class
   * @return spring bean
   */
  public static <T> T getBean(Class<T> clazz) {
    return APPLICATION_CONTEXT.getBean(clazz);
  }
}
