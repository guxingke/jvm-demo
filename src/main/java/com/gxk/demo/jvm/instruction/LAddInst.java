package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;

public class LAddInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Long a1 = frame.popLong();
    Long a2 = frame.popLong();
    frame.pushLong(a1 + a2);
  }
}
