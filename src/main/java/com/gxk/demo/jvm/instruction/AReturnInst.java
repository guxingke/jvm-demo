package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;

public class AReturnInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Object tmp = frame.popRef();
    frame.thread.popFrame();
    if (!frame.thread.empty()) {
      frame.thread.currentFrame().pushRef(tmp);
    }
//    System.out.println("do ret " + tmp);
  }

  @Override
  public String format() {
    return "areturn";
  }
}
