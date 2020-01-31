package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;

public class FLoad0Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    float tmp = frame.getFloat(0);
    frame.pushFloat(tmp);
  }
}