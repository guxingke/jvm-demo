package com.gxk.demo.jvm.classfile.cp;


import com.gxk.demo.jvm.classfile.ConstantInfo;
import com.gxk.demo.jvm.classfile.ConstantPoolInfoEnum;

public class ClassCp extends ConstantInfo {

  public final int nameIndex;

  public ClassCp(int infoEnum, int nameIndex) {
    super(infoEnum);
    this.nameIndex = nameIndex;
  }
}
