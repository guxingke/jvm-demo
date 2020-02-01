package com.gxk.demo.jvm.classpath;

import com.gxk.demo.jvm.classfile.ClassFile;
import com.gxk.demo.jvm.classfile.ClassReader;

import java.io.File;
import java.io.IOException;

public class DirEntry implements Entry {

  public final String dirPath;

  public DirEntry(String dirPath) {
    this.dirPath = dirPath;
  }

  @Override
  public ClassFile findClass(String clazzName) {

    String path = null;
    if (!clazzName.contains("/")) {
      String[] files = new File(dirPath).list();
      for (String file : files) {
        if (file.equals(clazzName + ".class")) {
          path = dirPath + "/" + file;
        }
      }
      if (path == null) {
        return null;
      }

      try {
        ClassFile cf = ClassReader.read(path, clazzName);
        cf.setSource(path.toString());
        return cf;
      } catch (IOException e) {
        e.printStackTrace();
      }

      return null;
    }

    int idx = clazzName.indexOf("/");
    String subDir = clazzName.substring(0, idx);
    String subPath = dirPath + "/" + subDir;
    if (!new File(subPath).exists()) {
      return null;
    }
    return new DirEntry(subPath).findClass(clazzName.substring(idx + 1));
  }
}
