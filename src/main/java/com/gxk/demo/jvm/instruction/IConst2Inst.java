package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;

public class IConst2Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    frame.pushInt(2);
  }

  @Override
  public String format() {
    return "iconst_2";
  }
}
