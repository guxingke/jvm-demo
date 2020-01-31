package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;

public class PopInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    frame.popRef();
  }

  @Override
  public String format() {
    return "pop";
  }
}
