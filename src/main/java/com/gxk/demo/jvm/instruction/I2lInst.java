package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;

public class I2lInst implements Instruction{

  @Override
  public int offset() {
    return 1;
  }

  @Override
  public void execute(Frame frame) {
    Integer tmp = frame.popInt();
    frame.pushLong(tmp.longValue());
  }
}