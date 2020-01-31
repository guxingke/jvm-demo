package com.gxk.demo.jvm.classfile.cp;

import com.gxk.demo.jvm.classfile.ConstantInfo;
import com.gxk.demo.jvm.classfile.ConstantPoolInfoEnum;

public class MethodType extends ConstantInfo {

  public final int descriptorIndex;

  public MethodType(int infoEnum, int descriptorIndex) {
    super(infoEnum);
    this.descriptorIndex = descriptorIndex;
  }
}
