package com.gxk.demo.jvm.nativebridge.java.lang;

import com.gxk.demo.jvm.rtda.heap.Heap;
import com.gxk.demo.jvm.rtda.heap.KArray;
import com.gxk.demo.jvm.rtda.heap.KObject;
import com.gxk.demo.jvm.util.Utils;

public abstract class SystemBridge {

  public static void registerNatives0() {
    Heap.registerMethod("java/lang/System_registerNatives_()V", (frame) -> {
    });

    Heap.registerMethod("java/lang/System_setIn0_(Ljava/io/InputStream;)V", (frame) -> {
    });
    Heap.registerMethod("java/lang/System_setOut0_(Ljava/io/PrintStream;)V", (frame) -> {
    });
    Heap.registerMethod("java/lang/System_setErr0_(Ljava/io/PrintStream;)V", (frame) -> {
    });
    Heap.registerMethod("java/lang/System_currentTimeMillis_()J", (frame) -> frame.pushLong(System.currentTimeMillis()));
    Heap.registerMethod("java/lang/System_nanoTime_()J", (frame) -> frame.pushLong(System.nanoTime()));
    Heap.registerMethod("java/lang/System_arraycopy_(Ljava/lang/Object;ILjava/lang/Object;II)V", (frame) -> {
      Integer len = frame.popInt();
      Integer dsp = frame.popInt();
      KArray dest = (KArray) frame.popRef();
      Integer ssp = frame.popInt();
      KArray source = (KArray) frame.popRef();

      for (int i = 0; i < len; i++) {
        dest.items[dsp++] = source.items[ssp++];
      }
    });
    Heap.registerMethod("java/lang/System_identityHashCode_(Ljava/lang/Object;)I", (frame) -> frame.pushInt(frame.popRef().hashCode()));
    Heap.registerMethod("java/lang/System_initProperties_(Ljava/util/Properties;)Ljava/util/Properties;", (frame) -> {
    });
    Heap.registerMethod("java/lang/System_mapLibraryName_(Ljava/lang/String;)Ljava/lang/String;", (frame) -> {
    });

    // hack
    Heap.registerMethod("java/lang/System_getEnv_(Ljava/lang/String;)Ljava/lang/String;", frame -> {
      KObject nameObj = (KObject) frame.popRef();
      frame.popRef();

      String val = System.getenv(Utils.obj2Str(nameObj));
      if (val == null) {
        frame.pushRef(null);
        return;
      }
      frame.pushRef(Utils.str2Obj(val, frame.method.clazz.classLoader));
    });
  }
}
