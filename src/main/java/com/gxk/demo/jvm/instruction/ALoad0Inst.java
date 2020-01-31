package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;

public class ALoad0Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Object tmp = frame.getRef(0);
    frame.pushRef(tmp);
  }

  @Override
  public String format() {
    return "aload_0";
  }
}
