package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;
import com.gxk.demo.jvm.rtda.heap.KArray;

public class LALoadInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Integer index = frame.popInt();
    KArray array = (KArray) frame.popRef();
    long item = (Long) array.items[index];
    frame.pushLong(item);
  }

  @Override
  public String format() {
    return "laload";
  }
}