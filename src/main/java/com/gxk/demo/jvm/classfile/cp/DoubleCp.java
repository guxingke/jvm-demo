package com.gxk.demo.jvm.classfile.cp;


import com.gxk.demo.jvm.classfile.ConstantInfo;
import com.gxk.demo.jvm.classfile.ConstantPoolInfoEnum;

public class DoubleCp extends ConstantInfo {

  public final double val;

  public DoubleCp(int infoEnum, double val) {
    super(infoEnum);
    this.val= val;
  }
}