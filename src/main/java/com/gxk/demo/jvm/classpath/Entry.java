package com.gxk.demo.jvm.classpath;

import com.gxk.demo.jvm.classfile.ClassFile;

public interface Entry {

  ClassFile findClass(String name);
}
