package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;
import com.gxk.demo.jvm.rtda.heap.Heap;
import com.gxk.demo.jvm.rtda.heap.KClass;
import com.gxk.demo.jvm.rtda.heap.KMethod;
import com.gxk.demo.jvm.rtda.heap.KObject;
import com.gxk.demo.jvm.rtda.heap.NativeMethod;
import com.gxk.demo.jvm.util.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class InvokeVirtualInst implements Instruction {

  public final String clazz;
  public final String methodName;
  public final String methodDescriptor;

  public InvokeVirtualInst(String clazz, String methodName, String methodDescriptor) {
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
    if (Objects.equals("sun/misc/Unsafe", clazz)
      || Objects.equals("java/util/Properties", clazz)) {
      NativeMethod nativeMethod = Heap.findMethod(Utils.genNativeMethodKey(clazz, methodName, methodDescriptor));
      if (nativeMethod != null) {
        nativeMethod.invoke(frame);
        return;
      }
    }

    if (Objects.equals("java/io/PrintStream", clazz)) {
      if (Objects.equals("println", methodName)) {
        switch (methodDescriptor) {
          case "(I)V":
            Integer val = frame.popInt();
            frame.popRef();
            System.out.println(val);
            return;
          case "(Ljava/lang/String;)V":
            KObject obj = (KObject) frame.popRef();
            String str = Utils.obj2Str(obj);
            frame.popRef();
            System.out.println(str);
            return;
          default:
            throw new UnsupportedOperationException();
        }
      }
    }

    KClass clazz = Heap.findClass(this.clazz);
    KMethod method = clazz.getMethod(methodName, methodDescriptor);

    if (method == null) {
      // try find interfaces
      if (clazz.interfaceNames.isEmpty()) {
        throw new IllegalStateException();
      }

      // already load interface
      if (!clazz.getInterfaces().isEmpty()) {
        for (KClass intClass : clazz.getInterfaces()) {
          method = intClass.getMethod(methodName, methodDescriptor);
          if (method != null) {
            break;
          }
        }
      } else {
        clazz.interfaceInit(frame);
        return;
      }
    }

    if (method == null) {
      throw new IllegalStateException();
    }

    // super method
    // fill args
    List<String> args = method.getArgs();
    List<Object> argObjs = new ArrayList<>();
    for (int i = args.size() - 1; i >= 0; i--) {
      String arg = args.get(i);
      argObjs.add(Utils.pop(frame, arg));
    }

    KObject ref = (KObject) frame.popRef();
    KMethod implMethod = ref.clazz.getMethod(methodName, methodDescriptor);

    NativeMethod nm = Heap.findMethod(Utils.genNativeMethodKey(implMethod));
    if (nm != null) {
      // restore frame
      ArrayList<String> tmpArgs = new ArrayList<>(args);
      Collections.reverse(tmpArgs);

      frame.pushRef(ref);
      for (int i = 0; i < tmpArgs.size(); i++) {
        String arg = tmpArgs.get(i);
        Object obj = argObjs.get(argObjs.size() - 1 - i);
        Utils.push(frame, arg, obj);
      }

      nm.invoke(frame);
      return;
    }

    Collections.reverse(argObjs);

    Frame newFrame = new Frame(implMethod, frame.thread);

    int slotIdx = 1;
    for (int i = 0; i < args.size(); i++) {
      String arg = args.get(i);
      slotIdx += Utils.setLocals(newFrame, slotIdx, arg, argObjs.get(i));
    }

    newFrame.setRef(0, ref);
    frame.thread.pushFrame(newFrame);
  }

  @Override
  public String format() {
    return "invokevirtual " + clazz + " " + methodName + " " + methodDescriptor;
  }

  @Override
  public String toString() {
    return "InvokeVirtualInst{" +
      "clazz='" + clazz + '\'' +
      ", methodName='" + methodName + '\'' +
      ", methodDescriptor='" + methodDescriptor + '\'' +
      '}';
  }
}
