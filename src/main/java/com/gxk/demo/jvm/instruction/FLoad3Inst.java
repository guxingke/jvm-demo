package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;

public class FLoad3Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    float tmp = frame.getFloat(3);
    frame.pushFloat(tmp);
  }
}