package com.gxk.demo.jvm.instruction;

import com.gxk.demo.jvm.rtda.Frame;
import com.gxk.demo.jvm.rtda.Slot;
import com.gxk.demo.jvm.rtda.heap.Heap;
import com.gxk.demo.jvm.rtda.heap.KArray;
import com.gxk.demo.jvm.rtda.heap.KClass;
import com.gxk.demo.jvm.rtda.heap.KField;
import com.gxk.demo.jvm.rtda.heap.KObject;

public class LdcWInst implements Instruction {
  public final String descriptor;
  public final Object val;

  @Override
  public int offset() {
    return 2;
  }

  public LdcWInst(String descriptor, Object val) {
    this.descriptor = descriptor;
    this.val = val;
  }

  @Override
  public void execute(Frame frame) {
    switch (descriptor) {
      case "I":
        frame.pushInt(((Integer) val));
        break;
      case "F":
        frame.pushFloat(((float) val));
        break;
      case "Ljava/lang/String":
        KClass klass = Heap.findClass("java/lang/String");
        if (klass == null) {
          klass = frame.method.clazz.classLoader.loadClass("java/lang/String");
        }
        if (!klass.isStaticInit()) {
          Frame newFrame = new Frame(klass.getMethod("<clinit>", "()V"), frame.thread);
          klass.setStaticInit(1);
          KClass finalKlass = klass;
          newFrame.setOnPop(() -> finalKlass.setStaticInit(2));
          frame.thread.pushFrame(newFrame);

          frame.nextPc = frame.thread.getPc();
          return;
        }
        KObject object = klass.newObject();
        KField field = object.getField("value", "[C");
        KClass arrClazz = new KClass(1, "[C", frame.method.clazz.classLoader, null);

        char[] chars = val.toString().toCharArray();
        Character[] characters = new Character[chars.length];
        for (int i = 0; i < chars.length; i++) {
          characters[i] = chars[i];
        }
        KArray arr = new KArray(arrClazz, characters);
        field.val = new Slot[] {new Slot(arr)};
        frame.pushRef(object);
        break;
      default:
        frame.pushRef(val);
        break;
    }
  }

  @Override
  public String format() {
    return "ldcw " + descriptor + " " + val;
  }
}
