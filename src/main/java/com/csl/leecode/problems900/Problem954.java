package com.csl.leecode.problems900;

import java.util.Arrays;

public class Problem954 {

  public static void main(String[] args) {
    int[] A = { 1, 2, 4, 8 };
    System.out.println(new Problem954().canReorderDoubled2(A));
  }

  public boolean canReorderDoubled(int[] A) {
    byte[] used = new byte[A.length];
    Integer B[] = new Integer[A.length];
    int bi = 0;
    for (int a : A) {
      B[bi++] = a;
    }
    Arrays.sort(B, (a, b) -> {
      int abc = Math.abs(a) - Math.abs(b);
      if (abc == 0) {
        return a - b;
      }
      return abc;
    });
    int j = 1;
    int suc = 0;
    for (int i = 0; i < B.length; i++) {
      if (used[i] == 1) {
        continue;
      }
      int newValue = B[i] << 1;
      if (j < i + 1) {
        j = i + 1;
      }
      for (; j < B.length; j++) {
        if (used[j] == 1) {
          continue;
        }
        if (B[j] == newValue) {
          used[i] = 1;
          used[j] = 1;
          suc++;
          break;
        }
      }
      // return false;
    }
    if (suc == B.length / 2) {
      return true;
    }
    return false;
  }

  public boolean canReorderDoubled2(int[] A) {
    int zeroLength = Arrays.stream(A).parallel().filter(a -> a == 0).toArray().length;
    if (zeroLength % 2 == 1) {
      return false;
    }
    int[] numb1 = Arrays.stream(A).parallel().filter(j -> j > 0).sorted().toArray();
    if (numb1.length % 2 == 1) {
      return false;
    }
    if (isPair(numb1)) {
      int[] array = Arrays.stream(A).parallel().filter(i -> i < 0).sorted().toArray();

      if (numb1.length % 2 == 1) {
        return false;
      }
      if (isPair(array)) {
        return true;
      }
    }
    return false;
  }

  private boolean isPair(int[] A) {
    byte[] used = new byte[A.length];
    int j = 1;
    for (int i = 0; i < A.length; i++) {
      boolean isHit = false;
      if (used[i] == 1) {
        continue;
      }
      int newValue = A[i] << 1;
      if (j < i + 1) {
        j = i + 1;
      }
      for (; j < A.length; j++) {
        if (used[j] == 1) {
          continue;
        }
        if (A[j] == newValue) {
          used[i] = 1;
          used[j] = 1;
          isHit = true;
          break;
        }
      }
      if (!isHit) {
        return false;
      }
      // return false;
    }
    return true;
  }
}