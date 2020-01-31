package com.gxk.demo.jvm.nativebridge.java.io;

import com.gxk.demo.jvm.rtda.heap.Heap;
import com.gxk.demo.jvm.rtda.heap.KObject;

import java.io.IOException;
import java.io.InputStream;

public abstract class NativeInputStreamBridge {
  public static void registerNatives0() {
    Heap.registerMethod("java/io/NativeInputStream_read_()I", frame -> {
      InputStream is = (InputStream) ((KObject) frame.popRef()).getExtra();
      try {
        int read = is.read();
        frame.pushInt(read);
      } catch (IOException e) {
        throw new UnsupportedOperationException();
      }
    });
    Heap.registerMethod("java/io/NativeInputStream_available_()I", frame -> {
      InputStream is = (InputStream) ((KObject) frame.popRef()).getExtra();
      try {
        int available = is.available();
        frame.pushInt(available);
      } catch (IOException e) {
        throw new UnsupportedOperationException();
      }
    });
    Heap.registerMethod("java/io/NativeInputStream_close_()V", frame -> {
      InputStream is = (InputStream) ((KObject) frame.popRef()).getExtra();
      try {
        is.close();
      } catch (IOException e) {
        throw new UnsupportedOperationException();
      }
    });
  }
}
