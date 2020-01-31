package com.gxk.demo.jvm.nativebridge.java.lang;

import com.gxk.demo.jvm.rtda.Slot;
import com.gxk.demo.jvm.rtda.heap.Heap;
import com.gxk.demo.jvm.rtda.heap.KClass;
import com.gxk.demo.jvm.rtda.heap.KObject;

public abstract class DoubleBridge {

  public static void registerNatives0() {
    Heap.registerMethod("java/lang/Double_doubleToRawLongBits_(D)J", frame -> {
      Double tmp = frame.popDouble();
      long v = Double.doubleToRawLongBits(tmp);
      frame.pushLong(v);
    });
    Heap.registerMethod("java/lang/Double_longBitsToDouble_(J)D", frame -> {
      Long tmp = frame.popLong();
      double v = Double.longBitsToDouble(tmp);
      frame.pushDouble(v);
    });

    Heap.registerMethod("java/lang/Double_valueOf_(D)Ljava/lang/Double;", frame -> {
      KClass clazz = Heap.findClass("java/lang/Double");
      KObject kObject = clazz.newObject();
      Slot v2 = frame.popSlot();
      Slot v1 = frame.popSlot();
      kObject.setField("value", "D", new Slot[]{v1, v2});
      frame.pushRef(kObject);
    });
  }
}
