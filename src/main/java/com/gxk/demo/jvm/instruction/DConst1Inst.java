package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;

public class DConst1Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    frame.pushDouble(0.0d);
  }
}
