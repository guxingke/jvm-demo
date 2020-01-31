package com.gxk.demo.jvm.nativebridge.java.lang;

import com.gxk.demo.jvm.rtda.heap.Heap;

public abstract class FloatBridge {

  public static void registerNatives0() {
    Heap.registerMethod("java/lang/Float_intBitsToFloat_(I)F", frame -> {
      Integer tmp = frame.popInt();
      float v = Float.intBitsToFloat(tmp);
      frame.pushFloat(v);
    });
    Heap.registerMethod("java/lang/Float_floatToRawIntBits_(F)I", frame -> {
      float tmp = frame.popFloat();
      int v = Float.floatToRawIntBits(tmp);
      frame.pushInt(v);
    });
  }
}
