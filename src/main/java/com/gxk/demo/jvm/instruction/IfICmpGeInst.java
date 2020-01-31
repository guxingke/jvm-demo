package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;

public class IfICmpGeInst implements Instruction {
  public final int offset;

  public IfICmpGeInst(int offset) {
    this.offset = offset;
  }

  @Override
  public int offset() {
    return 3;
  }

  @Override
  public void execute(Frame frame) {
    Integer val2= frame.popInt();
    Integer val1= frame.popInt();
    if (val1 >= val2) {
      frame.nextPc = frame.thread.getPc() + offset;
    }
  }

  @Override
  public String format() {
    return "if_icmp_ge";
  }
}
