package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.*;
import com.gxk.demo.jvm.rtda.Thread;
import com.gxk.demo.jvm.rtda.heap.*;

public class AThrowInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Thread thread = frame.thread;
    KObject exc = (KObject) frame.popRef();
    String name = exc.clazz.name;

    Integer handlerPc = thread.currentFrame().method.getHandlerPc(thread.getPc(), name);
    while (handlerPc == null && !thread.empty()) {
      thread.popFrame();
      if (thread.empty()) {
        break;
      }
      handlerPc = thread.currentFrame().method.getHandlerPc(thread.getPc(), name);
    }

    // no exception handler ...
    if (handlerPc == null) {
      System.out.println(exc);
      throw new RuntimeException("no exception handler");
    }

    thread.currentFrame().pushRef(exc);
    thread.currentFrame().nextPc = handlerPc;
  }

  @Override
  public String format() {
    return "athrow";
  }
}
