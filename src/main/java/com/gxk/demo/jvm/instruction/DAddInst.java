package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;

public class DAddInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    double a1 = frame.popDouble();
    double a2 = frame.popDouble();
    frame.pushDouble(a1 + a2);
  }
}
