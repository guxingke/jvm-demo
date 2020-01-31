package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;
import com.gxk.demo.jvm.rtda.Slot;
import com.gxk.demo.jvm.rtda.heap.KField;
import com.gxk.demo.jvm.rtda.heap.KObject;
import com.gxk.demo.jvm.util.Utils;

public class GetFieldInst implements Instruction {

  public final String clazz;
  public final String fieldName;
  public final String fieldDescriptor;

  @Override
  public int offset() {
    return 3;
  }

  public GetFieldInst(String clazz, String fieldName, String fieldDescriptor) {
    this.clazz = clazz;
    this.fieldName = fieldName;
    this.fieldDescriptor = fieldDescriptor;
  }


  @Override
  public void execute(Frame frame) {
    // hack for java/nio/charset/Charset name Ljava/lang/String;
    if (clazz.equals("java/nio/charset/Charset") && fieldName.equals("name")) {
      KObject obj = ((KObject) frame.popRef());
      KField field = obj.getField(fieldName, fieldDescriptor);
      field.val = new Slot[]{new Slot(Utils.str2Obj("UTF-8", obj.clazz.classLoader))};
      Slot[] val = field.val;

      for (Slot slot : val) {
        frame.pushSlot(slot);
      }
      return;
    }

    KObject obj = ((KObject) frame.popRef());
    KField field = obj.getField(fieldName, fieldDescriptor);
    Slot[] val = field.val;

    for (Slot slot : val) {
      frame.pushSlot(slot);
    }
  }

  @Override
  public String format() {
    return "getfield " + clazz + " " + fieldName + " " + fieldDescriptor;
  }

  @Override
  public String toString() {
    return "GetFieldInst{" +
        "clazz='" + clazz + '\'' +
        ", fieldName='" + fieldName + '\'' +
        ", fieldDescriptor='" + fieldDescriptor + '\'' +
        '}';
  }
}
