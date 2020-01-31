package com.gxk.demo.jvm.classfile.cp;

import com.gxk.demo.jvm.classfile.ConstantInfo;
import com.gxk.demo.jvm.classfile.ConstantPoolInfoEnum;
public class MethodHandle extends ConstantInfo {

  public final int referenceKind;
  public final int referenceIndex;

  public MethodHandle(int infoEnum, int referenceKind, int referenceIndex) {
    super(infoEnum);
    this.referenceKind = referenceKind;
    this.referenceIndex = referenceIndex;
  }
}
