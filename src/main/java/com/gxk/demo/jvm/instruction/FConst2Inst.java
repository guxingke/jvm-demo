package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;

public class FConst2Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    frame.pushFloat(2.0f);
  }
}
