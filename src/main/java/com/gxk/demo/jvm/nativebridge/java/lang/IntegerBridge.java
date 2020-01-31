package com.gxk.demo.jvm.nativebridge.java.lang;

import com.gxk.demo.jvm.rtda.Slot;
import com.gxk.demo.jvm.rtda.heap.Heap;
import com.gxk.demo.jvm.rtda.heap.KClass;
import com.gxk.demo.jvm.rtda.heap.KObject;

public abstract class IntegerBridge {

  public static void registerNatives0() {
    Heap.registerMethod("java/lang/Integer_valueOf_(I)Ljava/lang/Integer;", frame -> {
      KClass clazz = Heap.findClass("java/lang/Integer");
      KObject kObject = clazz.newObject();
      kObject.setField("value", "I", new Slot[] {new Slot(frame.popInt(), Slot.INT)});
      frame.pushRef(kObject);
    });
  }
}
