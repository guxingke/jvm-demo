package com.gxk.demo.jvm.rtda.heap;

import com.gxk.demo.jvm.util.EnvHolder;
import com.gxk.demo.jvm.util.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * jvm heap
 */
public abstract class Heap {
  private static final Map<String, NativeMethod> NATIVE_METHOD_MAP;
  private static final Map<String, KClass> STRING_K_CLASS_MAP;

  static {
    NATIVE_METHOD_MAP = new HashMap<>();
    STRING_K_CLASS_MAP = new HashMap<>();
  }

  public static void registerMethod(String key, NativeMethod method) {
    if (NATIVE_METHOD_MAP.containsKey(key)) {
      throw new IllegalStateException();
    }
    NATIVE_METHOD_MAP.put(key, method);
  }

  public static NativeMethod findMethod(String key) {
    return NATIVE_METHOD_MAP.get(key);
  }

  public static KClass findClass(String name) {
    return STRING_K_CLASS_MAP.get(name);
  }

  public static void registerClass(String name, KClass clazz) {
    if (EnvHolder.verboseClass) {
      String source = clazz.classLoader.getName();
      if (clazz.classFile != null && clazz.classFile.getSource() != null) {
        source = clazz.classFile.getSource();
      }
      Logger.clazz("[Loaded {} from {}]", name, source);
    }
    STRING_K_CLASS_MAP.putIfAbsent(name, clazz);
  }

  public static List<KClass> getClasses() {
    return new ArrayList<>(STRING_K_CLASS_MAP.values());
  }

  // for test
  public static void clear() {
    NATIVE_METHOD_MAP.clear();
    STRING_K_CLASS_MAP.clear();
  }
}
