package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;

public class IloadNInst implements Instruction {
  public final int n;

  public IloadNInst(int n) {
    this.n = n;
  }

  @Override
  public int offset() {
    return 2;
  }

  @Override
  public void execute(Frame frame) {
    Integer tmp = frame.getInt(n);
    frame.pushInt(tmp);
  }

  @Override
  public String format() {
    return "iload " + n;
  }
}
