package com.gxk.demo.jvm.rtda.heap;

import com.gxk.demo.jvm.rtda.Frame;

public interface NativeMethod {
  // do all things
  void invoke(Frame frame);
}
