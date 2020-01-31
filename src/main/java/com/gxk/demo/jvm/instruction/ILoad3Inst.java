package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;

public class ILoad3Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Integer tmp = frame.getInt(3);
    frame.pushInt(tmp);
  }

  @Override
  public String format() {
    return "iload_3";
  }
}
