package com.gxk.demo.jvm.classfile;

import com.gxk.demo.jvm.instruction.Instruction;

import java.util.Map;

public class CodeFromByte {

  private final Map<Integer, Instruction> instructions;

  public CodeFromByte(Map<Integer, Instruction> instructions) {
    this.instructions = instructions;
  }

  public Instruction getInst(int pc) {
    return this.instructions.get(pc);
  }
}
