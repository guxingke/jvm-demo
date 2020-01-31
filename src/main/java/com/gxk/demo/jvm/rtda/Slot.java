package com.gxk.demo.jvm.rtda;

public class Slot {
  // who you are?
  public static final int BYTE = 0;
  public static final int SHORT = 1;
  public static final int INT = 2;
  public static final int FLOAT = 3;

  public static final int REF = 4;

  public static final int LONG_LOW = 5;
  public static final int LONG_HIGH = 6;
  public static final int DOUBLE_LOW = 7;
  public static final int DOUBLE_HIGH = 8;

  public final Integer num;
  public final Object ref;

  public final int type;

  public Slot(Integer num, int type) {
    this.num = num;
    this.ref = null;
    this.type = type;
  }

  public Slot(Object ref) {
    this.num = null;
    this.ref = ref;
    this.type = REF;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Slot{");
    sb.append("num=").append(num);
    sb.append(", ref=").append(ref);
    sb.append('}');
    return sb.toString();
  }
}
