package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;

public class LLoad2Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Long tmp = frame.getLong(2);
    frame.pushLong(tmp);
  }

  @Override
  public String format() {
    return "lload2";
  }
}