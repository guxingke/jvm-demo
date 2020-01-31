package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;

public class DLoadInst implements Instruction {
  public final int index;

  public DLoadInst(int index) {
    this.index = index;
  }

  @Override
  public int offset() {
    return 2;
  }

  @Override
  public void execute(Frame frame) {
    double tmp = frame.getDouble(index);
    frame.pushDouble(tmp);
  }
}