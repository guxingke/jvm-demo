package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;

public class FStore1Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    float tmp = frame.popFloat();
    frame.setFloat(1, tmp);
  }
}
