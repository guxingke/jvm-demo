package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;

public class IConst3Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    frame.pushInt(3);
  }

  @Override
  public String format() {
    return "iconst_3";
  }
}
