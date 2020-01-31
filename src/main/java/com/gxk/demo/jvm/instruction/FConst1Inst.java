package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;

public class FConst1Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    frame.pushFloat(1.0f);
  }
}
