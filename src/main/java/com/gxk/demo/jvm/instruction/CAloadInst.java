package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;
import com.gxk.demo.jvm.rtda.heap.KArray;

public class CAloadInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Integer index = frame.popInt();
    KArray array = (KArray) frame.popRef();
    char item = (char) array.items[index];
    frame.pushInt(((int) (item)));
  }

  @Override
  public String format() {
    return "caload";
  }
}