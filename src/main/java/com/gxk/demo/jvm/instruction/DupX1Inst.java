package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;
import com.gxk.demo.jvm.rtda.Slot;

public class DupX1Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Slot s1 = frame.popSlot();
    Slot s2 = frame.popSlot();
    frame.pushSlot(s1);
    frame.pushSlot(s2);
    frame.pushSlot(s1);
  }

  @Override
  public String format() {
    return "dupx1";
  }
}