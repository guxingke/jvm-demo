package com.gxk.demo.jvm.nativebridge.java.lang;

import com.gxk.demo.jvm.rtda.heap.Heap;
import com.gxk.demo.jvm.rtda.heap.KArray;
import com.gxk.demo.jvm.rtda.heap.KObject;
import com.gxk.demo.jvm.util.Utils;

public abstract class StringBridge {

  public static void registerNatives0() {
    Heap.registerMethod("java/lang/String_intern_()Ljava/lang/String;", frame -> {
    });

    Heap.registerMethod("java/lang/String_getBytes_()[B", frame -> {
      KObject obj = (KObject) frame.popRef();
      String str = Utils.obj2Str(obj);
      byte[] bytes = str.getBytes();
      Byte[] byteObj = new Byte[bytes.length];
      for (int i = 0; i < bytes.length; i++) {
        byteObj[i] = bytes[i];
      }
      KArray arr = new KArray(Heap.findClass("[B"), byteObj);

      frame.pushRef(arr);
    });
  }
}
