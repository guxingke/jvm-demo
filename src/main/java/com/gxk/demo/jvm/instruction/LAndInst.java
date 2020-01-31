package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;

public class LAndInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    long a1 = frame.popLong();
    long a2 = frame.popLong();
    frame.pushLong(a2 & a1);
  }
}