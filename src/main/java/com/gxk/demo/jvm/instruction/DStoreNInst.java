package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;

public class DStoreNInst implements Instruction {
  public final int n;

  public DStoreNInst(int n) {
    this.n = n;
  }

  @Override
  public int offset() {
    return 2;
  }

  @Override
  public void execute(Frame frame) {
    double tmp = frame.popDouble();
    frame.setDouble(n, tmp);
  }
}
