package com.gxk.demo.jvm.classfile.cp;

import com.gxk.demo.jvm.classfile.ConstantInfo;
import com.gxk.demo.jvm.classfile.ConstantPoolInfoEnum;

public class InvokeDynamic extends ConstantInfo {

  public final int bootstrapMethodAttrIndex;
  public final int nameAndTypeIndex;

  public InvokeDynamic(int infoEnum, int bootstrapMethodAttrIndex, int nameAndTypeIndex) {
    super(infoEnum);
    this.bootstrapMethodAttrIndex = bootstrapMethodAttrIndex;
    this.nameAndTypeIndex = nameAndTypeIndex;
  }
}
