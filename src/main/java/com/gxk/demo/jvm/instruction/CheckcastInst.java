package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;

public class CheckcastInst implements Instruction {

  public final String clazz;

  public CheckcastInst(String clazz) {
    this.clazz = clazz;
  }

  @Override
  public int offset() {
    return 3;
  }

  @Override
  public void execute(Frame frame) {
//FIXME    throw new UnsupportedOperationException();
  }

  @Override
  public String format() {
    return "checkcast " + clazz;
  }
}
