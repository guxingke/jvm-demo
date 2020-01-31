package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;

public class AStoreInst implements Instruction {

  public final int index;

  public AStoreInst(int index) {
    this.index = index;
  }

  @Override
  public int offset() {
    return 2;
  }

  @Override
  public void execute(Frame frame) {
    Object tmp = frame.popRef();
    frame.setRef(index, tmp);
  }

  @Override
  public String format() {
    return "astore " + index;
  }

}