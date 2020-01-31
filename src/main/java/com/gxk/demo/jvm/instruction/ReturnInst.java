package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;

public class ReturnInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    // do nothing
    frame.thread.popFrame();
  }

  @Override
  public String format() {
    return "return";
  }
}
