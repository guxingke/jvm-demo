package com.gxk.demo.jvm.classloader;

import com.gxk.demo.jvm.classfile.ClassFile;
import com.gxk.demo.jvm.classfile.Field;
import com.gxk.demo.jvm.classfile.Interface;
import com.gxk.demo.jvm.classfile.Method;
import com.gxk.demo.jvm.classfile.attribute.BootstrapMethods;
import com.gxk.demo.jvm.classfile.attribute.Code;
import com.gxk.demo.jvm.classpath.Entry;
import com.gxk.demo.jvm.rtda.Slot;
import com.gxk.demo.jvm.rtda.heap.Heap;
import com.gxk.demo.jvm.rtda.heap.KClass;
import com.gxk.demo.jvm.rtda.heap.KField;
import com.gxk.demo.jvm.rtda.heap.KMethod;
import com.gxk.demo.jvm.rtda.heap.KObject;
import com.gxk.demo.jvm.rtda.heap.NativeMethod;
import com.gxk.demo.jvm.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class ClassLoader {

  private final String name;
  private final Entry entry;

  public ClassLoader(String name, Entry entry) {
    this.name = name;
    this.entry = entry;
  }

  public void loadPrimitiveClass(String name) {
    KClass cache = Heap.findClass(name);
    if (cache != null) {
      return;
    }
    KClass cls = new KClass(1, name, this);
    KObject metaCls = Heap.findClass("java/lang/Class").newObject();
    cls.setRuntimeClass(metaCls);
    metaCls.setMetaClass(cls);

    doRegister(cls);
  }

  public void loadPrimitiveArrayClass(String name) {
    KClass cache = Heap.findClass(name);
    if (cache != null) {
      return;
    }
    KClass cls = new KClass(1, name, this);
    KObject metaCls = Heap.findClass("java/lang/Class").newObject();
    cls.setRuntimeClass(metaCls);
    metaCls.setMetaClass(cls);

    doRegister(cls);
  }

  public KClass loadClass(String name) {
    KClass cache = Heap.findClass(name);
    if (cache != null) {
      return cache;
    }

    KClass clazz = doLoadClass(name);
    doRegister(clazz);

    return clazz;
  }

  public void doRegister(KClass clazz) {
    Heap.registerClass(clazz.name, clazz);
    for (KMethod method : clazz.methods) {
      if (method.isNative()) {
        String key = Utils.genNativeMethodKey(method.clazz.name, method.name, method.descriptor);
        NativeMethod nm = Heap.findMethod(key);
        if (nm == null) {
          System.err.println("not found native method " + key + " " + method);
        }
      }
    }
  }

  public KClass doLoadClass(String name) {
    ClassFile clazz = entry.findClass(name);
    KClass kClass = doLoadClass(name, clazz);

    // superclass
    if (kClass.superClassName != null) {
      kClass.setSuperClass(this.loadClass(kClass.superClassName));
    }

    if (Heap.findClass("java/lang/Class") != null) {
      KObject rcs = Heap.findClass("java/lang/Class").newObject();
      kClass.setRuntimeClass(rcs);
      rcs.setMetaClass(kClass);
    }

    return kClass;
  }

  public KClass doLoadClass(String name, ClassFile classFile) {
    List<KMethod> methods = new ArrayList<>();
    for (Method method : classFile.methods.methods) {
      KMethod map = this.map(method);
      methods.add(map);
    }
    List<KField> fields = new ArrayList<>();
    for (Field field : classFile.fields.fields) {
      KField map = this.map(field);
      fields.add(map);
    }

    // field interfaceInit
    for (KField it: fields) {
      switch (it.descriptor) {
        case "Z":
        case "C":
        case "B":
        case "S":
        case "I":
          it.val = new Slot[] {new Slot(0, Slot.INT)};
        case "F":
          it.val = new Slot[] {new Slot(0, Slot.FLOAT)};
          break;
        case "D":
          it.val = new Slot[] {new Slot(0,Slot.DOUBLE_HIGH), new Slot(0, Slot.DOUBLE_LOW)};
        case "J":
          it.val = new Slot[] {new Slot(0,Slot.LONG_HIGH), new Slot(0, Slot.LONG_LOW)};
          break;
        default:
          it.val = new Slot[] {new Slot(null)};
      }

    }
    int scIdx = classFile.superClass;
    String superClassName = null;
    if (scIdx != 0) {
      superClassName = Utils.getClassName(classFile.cpInfo, scIdx);
    }

    List<String> interfaceNames = new ArrayList<>();
    if (classFile.interfaces.interfaces.length != 0) {
      for (Interface ai : classFile.interfaces.interfaces) {
        interfaceNames.add(ai.getName());
      }
    }

    BootstrapMethods bootstrapMethods = classFile.getBootstrapMethods();

    return new KClass(classFile.accessFlags, name, superClassName, interfaceNames, methods, fields, bootstrapMethods, classFile.cpInfo, this, classFile);
  }

  public KMethod map(Method cfMethod) {
    Code code = cfMethod.getCode();
    if (code == null) {
      return new KMethod(cfMethod.accessFlags, cfMethod.name, cfMethod.descriptor.descriptor, 0, 0, null, null);
    }
    return new KMethod(cfMethod.accessFlags, cfMethod.name, cfMethod.descriptor.descriptor, code.maxStacks, code.maxLocals, code.getInstructions(), code.exceptionTable);
  }

  public KField map(Field field) {
    return new KField(field.accessFlags, field.name, field.descriptor.descriptor);
  }

  public String getName() {
    return name;
  }
}
