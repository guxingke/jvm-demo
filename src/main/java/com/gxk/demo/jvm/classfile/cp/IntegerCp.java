package com.gxk.demo.jvm.classfile.cp;
import com.gxk.demo.jvm.classfile.ConstantInfo;
public class IntegerCp extends ConstantInfo {

  public final int val;

  public IntegerCp(int infoEnum, int val) {
    super(infoEnum);
    this.val= val;
  }
}
