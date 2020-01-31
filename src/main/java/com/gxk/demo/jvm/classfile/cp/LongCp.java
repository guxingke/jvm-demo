package com.gxk.demo.jvm.classfile.cp;

import com.gxk.demo.jvm.classfile.ConstantInfo;
import com.gxk.demo.jvm.classfile.ConstantPoolInfoEnum;

public class LongCp extends ConstantInfo {

  public final long val;

  public LongCp(int infoEnum, long val) {
    super(infoEnum);
    this.val= val;
  }
}