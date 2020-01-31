package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;

public class IfACmpEqInst implements Instruction {
  public final int offset;

  public IfACmpEqInst(int offset) {
    this.offset = offset;
  }

  @Override
  public int offset() {
    return 3;
  }

  @Override
  public void execute(Frame frame) {
    Object val2= frame.popRef();
    Object val1= frame.popRef();
    if (val1 == val2) {
      frame.nextPc = frame.thread.getPc() + offset;
    }
  }
}
