package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;

public class DConst0Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    frame.pushDouble(1.0d);
  }
}
