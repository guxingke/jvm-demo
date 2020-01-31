package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;

public class DLoad0Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    double tmp = frame.getDouble(0);
    frame.pushDouble(tmp);
  }
}
