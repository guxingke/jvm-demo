package com.gxk.demo.jvm.nativebridge.java.security;

import com.gxk.demo.jvm.rtda.Frame;
import com.gxk.demo.jvm.rtda.heap.Heap;
import com.gxk.demo.jvm.rtda.heap.KMethod;
import com.gxk.demo.jvm.rtda.heap.KObject;

public abstract class AccessControllerBridge {

  public static void registerNative0() {
    // static
    Heap.registerMethod("java/security/AccessController_doPrivileged_(Ljava/security/PrivilegedAction;)Ljava/lang/Object;", frame -> {
      KObject thisObj = (KObject) frame.popRef();
      KMethod method = thisObj.clazz.getMethod("run", "()Ljava/lang/Object;");
      Frame newFrame = new Frame(method, frame.thread);
      newFrame.setRef(0, thisObj);
      frame.thread.pushFrame(newFrame);
    });
    // static
    Heap.registerMethod("java/security/AccessController_doPrivileged_(Ljava/security/PrivilegedExceptionAction;)Ljava/lang/Object;", frame -> {
      KObject thisObj = (KObject) frame.popRef();
      KMethod method = thisObj.clazz.getMethod("run", "()Ljava/lang/Object;");
      Frame newFrame = new Frame(method, frame.thread);
      newFrame.setRef(0, thisObj);
      frame.thread.pushFrame(newFrame);
    });
  }
}
