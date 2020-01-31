package com.gxk.demo.jvm.rtda.heap;

import java.util.List;

public class KLambdaObject extends KObject{
  public final List<Object> args;

  public KLambdaObject(KClass clazz, List<Object> vars) {
    super(clazz);
    this.args = vars;
  }
}
