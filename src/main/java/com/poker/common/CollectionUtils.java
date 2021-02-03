package com.poker.common;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Collection utils similar to Java 11 Collections API features
 *
 * @author enderdincer
 */
public final class CollectionUtils {

  private CollectionUtils(){}

  public static <K, V, M extends Map<K, V>> M mapOf(Supplier<M> mapSupplier, Object... keyValues) {
    if (Objects.isNull(keyValues)) {
      return null;
    }

    M map = mapSupplier.get();

    for (int i = 0; i < keyValues.length; i = i + 2) {
      map.put((K) keyValues[i], (V) keyValues[i + 1]);
    }
    return map;
  }

  public static <T, L extends List<T>> L listOf(Supplier<L> listSupplier, Object... values) {
    if (Objects.isNull(values)) {
      return null;
    }
    L list = listSupplier.get();

    for (Object value : values) {
      list.add((T) value);
    }

    return list;
  }
}
