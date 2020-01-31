package com.gxk.demo.jvm;

import com.gxk.demo.jvm.classloader.ClassLoader;
import com.gxk.demo.jvm.classpath.DirEntry;
import com.gxk.demo.jvm.interpret.Interpreter;
import com.gxk.demo.jvm.rtda.heap.KClass;
import com.gxk.demo.jvm.rtda.heap.KMethod;

import java.io.IOException;

public class Main {

  public static void main(String[] args) throws IOException {
    if (args.length == 0) {
      printHelp();
      return;
    }

    Args cmd = Args.parseArgs(args);

    if (cmd.version) {
      System.out.println("java version \"1.8.0\"");
      return;
    }

    if (cmd.help) {
      printHelp();
      return;
    }

    ClassLoader boot = new ClassLoader("boot", new DirEntry("/Users/gxk/"));
    KClass clazz = boot.loadClass("HelloWorld");
    KMethod mainMethod = clazz.getMainMethod();

    new Interpreter().interpret(mainMethod);

//    VirtualMachine vm = new VirtualMachine();
//    vm.run(cmd);
  }

  private static void printHelp() {
//    InputStream is = Main.class.getClassLoader().getResourceAsStream("help.txt");
//    tryinterpret (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
//      StringBuilder out = new StringBuilder();
//      String line;
//      while ((line = reader.readLine()) != null) {
//        out.append(line).apinterpretpend("\n");
//      }
//      System.out.println(out);
//    } catch (Exception e) {
//      System.out.println("something bad");
//    }
    System.out.println("help!");
  }
}
