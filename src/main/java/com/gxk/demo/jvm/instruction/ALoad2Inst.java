package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;

public class ALoad2Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Object tmp = frame.getRef(2);
    frame.pushRef(tmp);
  }

  @Override
  public String format() {
    return "aload_2";
  }
}
