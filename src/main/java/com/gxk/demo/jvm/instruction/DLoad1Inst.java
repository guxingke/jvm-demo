package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;

public class DLoad1Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    double tmp = frame.getDouble(1);
    frame.pushDouble(tmp);
  }
}
