package com.gxk.demo.jvm.classpath;

import com.gxk.demo.jvm.util.EnvHolder;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class Classpath {

  public static Entry parse(String classpath) {
    if (classpath.contains(EnvHolder.PATH_SEPARATOR)) {

      return doParseCompositeEntry(classpath);
    }
    Entry entry = parseEntry(classpath);
    if (entry == null) {
      throw new IllegalArgumentException("un parse classpath " + classpath);
    }
    // ./:./*:lib/:lib/*:.*
    return entry;
  }

  public static Entry doParseCompositeEntry(String classpath) {

    List<Entry> entries = new ArrayList<>();

    for (String path : classpath.split(EnvHolder.PATH_SEPARATOR)) {
      Entry entry = parseEntry(path);
      if (entry == null) {
        throw new IllegalArgumentException("un parse classpath " + classpath);
      }
      entries.add(entry);
    }
    return new CompositeEntry(entries);
  }

  public static Entry parseEntry(String path) {
    Entry entry = null;
    if (isWildcard(path)) {
      entry = doParseWildcard(path.substring(0, path.length() - 2));
    } else if (isDir(path)) {
      entry = doParseDir(path);
    } else if (isJar(path)) {
      entry = doParseJar(path);
    }
    return entry;
  }

  public static Entry doParseWildcard(String path) {
    List<Entry> entries = Arrays.stream(
      Objects.requireNonNull(new File(path).list((dir, name) -> isJar(name))))
      .map(it -> Classpath.doParseJar(path + File.separator + it))
      .collect(Collectors.toList());
    return new CompositeEntry(entries);
  }

  public static Entry doParseJar(String path) {
    return new JarEntry(path);
  }

  public static Entry doParseDir(String path) {
    return new DirEntry(path);
  }

  public static boolean isDir(String path) {
    return new File(path).isDirectory();
  }

  public static boolean isWildcard(String path) {
    return path.endsWith(EnvHolder.FILE_SEPARATOR + "*");
  }

  public static boolean isJar(String path) {
    return path.endsWith(".jar");
  }
}
