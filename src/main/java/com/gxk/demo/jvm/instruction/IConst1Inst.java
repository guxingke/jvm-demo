package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;

/**
 * @see IConst0Inst
 */
public class IConst1Inst implements Instruction {

  @Override
  public void execute(Frame frame) {
    frame.pushInt(1);
  }

  @Override
  public String format() {
    return "iconst_1";
  }
}
