package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;
import com.gxk.demo.jvm.rtda.heap.Heap;
import com.gxk.demo.jvm.rtda.heap.KClass;
import com.gxk.demo.jvm.rtda.heap.KMethod;
import com.gxk.demo.jvm.rtda.heap.NativeMethod;

import com.gxk.demo.jvm.util.Utils;
import java.util.List;

public class InvokeSpecialInst implements Instruction {

  public final String clazz;
  public final String methodName;
  public final String methodDescriptor;

  public InvokeSpecialInst(String clazz, String methodName, String methodDescriptor) {
    this.clazz = clazz;
    this.methodName = methodName;
    this.methodDescriptor = methodDescriptor;
  }

  @Override
  public int offset() {
    return 3;
  }

  @Override
  public void execute(Frame frame) {
    NativeMethod nm = Heap.findMethod(Utils.genNativeMethodKey(clazz, methodName, methodDescriptor));
    if (nm != null) {
      nm.invoke(frame);
      return;
    }

    KClass kClass = Heap.findClass(clazz);
    if (kClass == null) {
      throw new IllegalStateException();
    }

    KMethod method = kClass.getMethod(methodName, methodDescriptor);
    if (method == null) {
      System.out.println(clazz);
      System.out.println(methodName);
      System.out.println(methodDescriptor);
      throw new IllegalStateException();
    }

    if (method.isNative()) {
      throw new IllegalStateException("un impl native method call, " + method);
    }

    Frame newFrame = new Frame(method, frame.thread);
    // fill args
    List<String> args = method.getArgs();
    int slotIdx = method.getArgSlotSize() + 1;

    int idx = args.size() - 1;
    while (idx >= 0) {
      String arg = args.get(idx);
      switch (arg) {
        case "I":
        case "B":
        case "C":
        case "S":
        case "Z":
          slotIdx--;
          newFrame.setInt(slotIdx, frame.popInt());
          break;
        case "J":
          slotIdx -= 2;
          newFrame.setLong(slotIdx, frame.popLong());
          break;
        case "F":
          slotIdx -= 1;
          newFrame.setFloat(slotIdx, frame.popFloat());
          break;
        case "D":
          slotIdx -= 2;
          newFrame.setDouble(slotIdx, frame.popDouble());
          idx -= 2;
          break;
        default:
          slotIdx--;
          newFrame.setRef(slotIdx, frame.popRef());
          break;
      }
      idx--;
    }

    newFrame.setRef(0, frame.popRef());
    frame.thread.pushFrame(newFrame);
  }

  @Override
  public String format() {
    return "invokespecail " + clazz + " " + methodName + " " + methodDescriptor;
  }
}
