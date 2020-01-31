package com.gxk.demo.jvm.nativebridge.java.util.concurrent;

import com.gxk.demo.jvm.rtda.heap.Heap;

public abstract class AtomicLongBridge {

  public static void registerNatives0() {
    Heap.registerMethod("java/util/concurrent/atomic/AtomicLong_VMSupportsCS8_()Z", frame -> {
      frame.pushInt(0);
    });
    Heap.registerMethod("java/util/concurrent/atomic/AtomicLong_<clinit>_()V", frame -> {
    });
  }
}
