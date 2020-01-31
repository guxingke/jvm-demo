package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;

public class FStoreNInst implements Instruction {
  public final int n;

  public FStoreNInst(int n) {
    this.n = n;
  }

  @Override
  public int offset() {
    return 2;
  }

  @Override
  public void execute(Frame frame) {
    Float tmp = frame.popFloat();
    frame.setFloat(n, tmp);
  }
}
