package com.gxk.demo.jvm.classfile.cp;

import com.gxk.demo.jvm.classfile.ConstantInfo;
import com.gxk.demo.jvm.classfile.ConstantPoolInfoEnum;


public class MethodDef extends ConstantInfo {

  public final int classIndex;
  public final int nameAndTypeIndex;

  public MethodDef(int infoEnum, int classIndex, int nameAndTypeIndex) {
    super(infoEnum);
    this.classIndex = classIndex;
    this.nameAndTypeIndex = nameAndTypeIndex;
  }
}
