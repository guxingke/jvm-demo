package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;
import com.gxk.demo.jvm.rtda.heap.KArray;

public class CAStoreInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Character val = ((char) (frame.popInt().intValue()));
    Integer index = frame.popInt();
    KArray array = (KArray) frame.popRef();
    array.items[index] = val;
  }

  @Override
  public String format() {
    return "castore";
  }
}