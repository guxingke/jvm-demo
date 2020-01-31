package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;

public class DRemInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    double v2 = frame.popDouble();
    double v1 = frame.popDouble();
    frame.pushDouble(v1 % v2);
  }
}