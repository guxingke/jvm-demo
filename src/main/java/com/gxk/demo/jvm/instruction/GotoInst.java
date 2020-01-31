package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;

public class GotoInst implements Instruction {

  public final short offset;

  public GotoInst(short offset) {
    this.offset = offset;
  }

  @Override
  public int offset() {
    return 3;
  }

  @Override
  public void execute(Frame frame) {
    frame.nextPc = frame.thread.getPc() + offset;
  }

  @Override
  public String format() {
    return String.format("%s %d", "goto", offset);
  }
}
