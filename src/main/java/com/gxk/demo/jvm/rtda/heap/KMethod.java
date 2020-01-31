package com.gxk.demo.jvm.rtda.heap;

import com.gxk.demo.jvm.classfile.ExceptionTable;
import com.gxk.demo.jvm.instruction.Instruction;
import com.gxk.demo.jvm.util.Utils;
import com.gxk.demo.jvm.classfile.Exception;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class KMethod {

  public final int accessFlags;
  public final String name;
  public final String descriptor;

  public final int maxStacks;
  public final int maxLocals;
  public final Map<Integer, Instruction> instructionMap;
  public final ExceptionTable exceptionTable;

  public KClass clazz;

  public KMethod(int accessFlags, String name, String descriptor, int maxStacks, int maxLocals,
      Map<Integer, Instruction> instructionMap, ExceptionTable exceptionTable) {
    this.accessFlags = accessFlags;
    this.name = name;
    this.descriptor = descriptor;
    this.maxStacks = maxStacks;
    this.maxLocals = maxLocals;
    this.instructionMap = instructionMap;
    this.exceptionTable = exceptionTable;
  }

  public String getReturnType() {
    return this.descriptor.substring(this.descriptor.indexOf(")") + 1);
  }

  public List<String> getArgs() {
    return Utils.parseMethodDescriptor(this.descriptor);
  }

  public int getArgSlotSize() {
    int count = Utils.parseMethodDescriptor(this.descriptor).stream()
        .map(it -> {
          if (Objects.equals("J", it)) {
            return 2;
          }
          if (Objects.equals("D", it)) {
            return 2;
          }
          return 1;
        })
        .reduce(0, (pre, next) -> pre + next);

    return count;
  }

  @Override
  public String toString() {
    return "KMethod{" +
        "accessFlags=" + accessFlags +
        ", name='" + name + '\'' +
        ", descriptor='" + descriptor + '\'' +
        ", maxStacks=" + maxStacks +
        ", maxLocals=" + maxLocals +
        ", instructionMap=" + instructionMap +
        ", clazz=" + clazz.name +
        '}';
  }

  public boolean isNative() {
    return (this.accessFlags & 0x0100) != 0;
  }

  public boolean isStatic() {
    return (this.accessFlags & 0x0008) != 0;
  }

  public String nativeMethodKey() {
    return String.format("%s_%s_%s", this.clazz.name, name, descriptor);
  }

  public Integer getHandlerPc(Integer pc, String name) {
    for (Exception exception : this.exceptionTable.exceptions) {
      if (exception.clazz == null || Objects.equals(exception.clazz, name)) {
        if (pc >= exception.startPc && pc < exception.endPc) {
          return exception.handlerPc;
        }
      }
    }
    return null;
  }
}
