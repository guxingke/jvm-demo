package com.gxk.demo.jvm.classfile.cp;

import com.gxk.demo.jvm.classfile.ConstantInfo;
import com.gxk.demo.jvm.classfile.ConstantPoolInfoEnum;

public class NameAndType extends ConstantInfo {

  public final int nameIndex;
  public final int descriptionIndex;

  public NameAndType(int infoEnum, int nameIndex, int descriptionIndex) {
    super(infoEnum);
    this.nameIndex = nameIndex;
    this.descriptionIndex = descriptionIndex;
  }
}
