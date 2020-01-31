package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;

public class LShlInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Integer v2 = frame.popInt();
    Long v1 = frame.popLong();
    int s = v2 & 0x1f;
    long ret = v1 << s;
    frame.pushLong(ret);
  }
}