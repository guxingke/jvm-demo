package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;

public class LLoad3Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Long tmp = frame.getLong(3);
    frame.pushLong(tmp);
  }
}