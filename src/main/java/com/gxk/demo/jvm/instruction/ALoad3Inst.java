package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;

public class ALoad3Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Object tmp = frame.getRef(3);
    frame.pushRef(tmp);
  }

  @Override
  public String format() {
    return "aload_3";
  }
}
