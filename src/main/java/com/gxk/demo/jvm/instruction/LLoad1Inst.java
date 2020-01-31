package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;

public class LLoad1Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Long tmp = frame.getLong(1);
    frame.pushLong(tmp);
  }
}
