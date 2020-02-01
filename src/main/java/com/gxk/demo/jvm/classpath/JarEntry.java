package com.gxk.demo.jvm.classpath;

import com.gxk.demo.jvm.classfile.ClassFile;
import com.gxk.demo.jvm.classfile.ClassReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class JarEntry implements Entry {

  public final String path;

  public JarEntry(String path) {
    this.path = path;
  }

  @Override
  public ClassFile findClass(String name) {
    ZipFile file;
    try {
      file = new ZipFile(path);
    } catch (IOException e) {
      throw new IllegalStateException();
    }

    ZipEntry jarEntry = file.getEntry(name + ".class");

    if (jarEntry == null) {
      return null;
    }

    try (InputStream is = file.getInputStream(jarEntry)) {
      ClassFile cf = ClassReader.read(new DataInputStream(is), name);
      cf.setSource(path.toString());

      return cf;
    } catch (Exception e) {
      e.printStackTrace();
      throw new IllegalStateException();
    }
  }
}
