package com.csl.leecode.problems900;

import java.util.ArrayList;
import java.util.List;

public class Problem955 {

  public int minDeletionSize(String[] A) {
    int sum = 0;
    int strLength = A[0].length();
    byte[] confirm = new byte[A.length];
    for (int i = 0; i < strLength; i++) {
      boolean isContinue = false;
      List<Integer> changed = new ArrayList<>();
      for (int j = 0; j < A.length - 1; j++) {
        if (confirm[j] == 1) {
          continue;
        }
        if ((A[j].charAt(i) - A[j + 1].charAt(i)) > 0) {
          sum++;
          isContinue = true;
          changed.clear();
          break;
        } else if ((A[j].charAt(i) - A[j + 1].charAt(i)) == 0) {
          isContinue = true;
        } else {
          changed.add(j);
        }
      }
      if (!isContinue) {
        return sum;
      }
      for (Integer a : changed) {
        confirm[a] = 1;
      }
    }
    return sum;
  }
}
