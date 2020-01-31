package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;

public class IConstM1Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    frame.pushInt(-1);
  }

  @Override
  public String format() {
    return "iconst_m1";
  }
}