package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;

public class ILoad0Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Integer tmp = frame.getInt(0);
    frame.pushInt(tmp);
  }

  @Override
  public String format() {
    return "iload_0";
  }
}
