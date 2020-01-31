package com.gxk.demo.jvm.classfile.cp;

import com.gxk.demo.jvm.classfile.ConstantInfo;
import com.gxk.demo.jvm.classfile.ConstantPoolInfoEnum;
public class FloatCp extends ConstantInfo {

  public final float val;

  public FloatCp(int infoEnum, float val) {
    super(infoEnum);
    this.val= val;
  }
}