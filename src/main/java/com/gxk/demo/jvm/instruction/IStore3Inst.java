package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;

public class IStore3Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Integer tmp = frame.popInt();
    frame.setInt(3, tmp);
  }

  @Override
  public String format() {
    return "istore_3";
  }
}
