package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;

public class ISubInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Integer v2 = frame.popInt();
    Integer v1 = frame.popInt();
    frame.pushInt(v1 - v2);
  }

  @Override
  public String format() {
    return "isub";
  }
}
