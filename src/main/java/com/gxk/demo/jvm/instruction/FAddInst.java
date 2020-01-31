package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;

public class FAddInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    float a1 = frame.popFloat();
    float a2 = frame.popFloat();
    frame.pushFloat(a1 + a2);
  }
}
