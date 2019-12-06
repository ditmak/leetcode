package com.csl.leecode.problems900;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Problem990 {
  public boolean equationsPossible(String[] equations) {
    List<String> unEquals = new ArrayList<>();
    List<Set<Character>> equals = new ArrayList<>();
    // Arrays.sort(equations);
    for (String equal : equations) {
      char a = equal.charAt(0);
      char b = equal.charAt(3);
      if (equal.charAt(1) == '!') {
        if (a == b) {
          return false;
        }
        unEquals.add(equal);
      } else {
        if (a == b) {
          continue;
        }
        boolean isHit = false;
        Set<Character> cur = null;
        for (Set<Character> set : equals) {
          if (set.contains(a)) {
            set.add(b);
            if (isHit) {
              cur.addAll(set);
              break;
            }
            isHit = true;
            cur = set;
            // break;
          } else if (set.contains(b)) {
            set.add(a);
            if (isHit) {
              cur.addAll(set);
              break;
            }
            isHit = true;
            cur = set;
            // break;
          }
        }
        if (!isHit) {
          Set<Character> set = new HashSet<>();
          set.add(a);
          set.add(b);
          equals.add(set);
        }
      }
    }

    for (String unEqual : unEquals) {
      char a = unEqual.charAt(0);
      char b = unEqual.charAt(3);
      for (Set<Character> set : equals) {
        if (set.contains(a)) {
          if (set.contains(b)) {
            return false;
          }
          break;
        }
        if (set.contains(b)) {
          if (set.contains(a)) {
            return false;
          }
          break;
        }
      }
    }
    return true;
  }
}
