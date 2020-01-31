package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.*;
import com.gxk.demo.jvm.classloader.ClassLoader;
import com.gxk.demo.jvm.rtda.heap.*;

public class NewInst implements Instruction {

  public final String clazz;

  public NewInst(String clazz) {
    this.clazz = clazz;
  }

  @Override
  public int offset() {
    return 3;
  }

  @Override
  public void execute(Frame frame) {
    KClass kClass = Heap.findClass(clazz);

    if (kClass == null) {
      ClassLoader loader = frame.method.clazz.classLoader;
      kClass = loader.loadClass(clazz);
    }

    if (kClass == null) {
      throw new IllegalStateException(ClassNotFoundException.class.getName());
    }

    if (!kClass.isStaticInit()) {
      // interfaceInit
      KMethod cinit = kClass.getClinitMethod();
      if (cinit == null) {
        kClass.setStaticInit(2);
        frame.nextPc = frame.thread.getPc();
        return;
      }

      String clNm = cinit.nativeMethodKey();
      NativeMethod clm = Heap.findMethod(clNm);
      if (clm != null) {
        clm.invoke(frame);
      } else {
        Frame newFrame = new Frame(cinit, frame.thread);
        kClass.setStaticInit(1);
        KClass finalKClass = kClass;
        newFrame.setOnPop(() -> finalKClass.setStaticInit(2));
        frame.thread.pushFrame(newFrame);

        frame.nextPc = frame.thread.getPc();
        return;
      }
    }

    KObject obj = kClass.newObject();
    frame.pushRef(obj);
  }

  @Override
  public String format() {
    return "new " + clazz;
  }
}

