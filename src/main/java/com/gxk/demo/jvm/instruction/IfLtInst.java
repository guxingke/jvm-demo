package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;

public class IfLtInst implements Instruction {
  public final int offset;

  public IfLtInst(int offset) {
    this.offset = offset;
  }

  @Override
  public int offset() {
    return 3;
  }

  @Override
  public void execute(Frame frame) {
    Integer val= frame.popInt();
    if (val < 0) {
      frame.nextPc = frame.thread.getPc() + offset;
    }
  }
}
