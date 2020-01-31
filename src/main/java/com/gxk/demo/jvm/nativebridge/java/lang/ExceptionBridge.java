package com.gxk.demo.jvm.nativebridge.java.lang;

import com.gxk.demo.jvm.rtda.Slot;
import com.gxk.demo.jvm.rtda.heap.Heap;
import com.gxk.demo.jvm.rtda.heap.KField;
import com.gxk.demo.jvm.rtda.heap.KObject;

public abstract class ExceptionBridge {

  public static void registerNatives0() {
    Heap.registerMethod("java/lang/Exception_<init>_(Ljava/lang/String;)V", frame -> {
      KObject str = (KObject) frame.popRef();
      KObject thisObj = (KObject) frame.popRef();
      KField msgField = thisObj.getField("detailMessage", "Ljava/lang/String;");
      msgField.val = new Slot[] {new Slot(str)};
    });
  }
}
