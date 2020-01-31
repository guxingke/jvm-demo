package com.gxk.demo.jvm.classfile.cp;

import com.gxk.demo.jvm.classfile.ConstantInfo;
import com.gxk.demo.jvm.classfile.ConstantPoolInfoEnum;

public class StringCp extends ConstantInfo {

  public final int stringIndex;

  public StringCp(int infoEnum, int stringIndex) {
    super(infoEnum);
    this.stringIndex = stringIndex;
  }
}
