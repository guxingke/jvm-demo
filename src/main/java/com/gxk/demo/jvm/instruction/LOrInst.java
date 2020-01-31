package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;

public class LOrInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Long v2 = frame.popLong();
    Long v1 = frame.popLong();
    frame.pushLong(v1 | v2);
  }
}