package com.csl.leecode.problems200;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Problem211 {
  private Set<String> data = new HashSet<>();
  private Map<String, Boolean> request = new HashMap<>();
  public Problem211() {
  }

  /** Adds a word into the data structure. */
  public void addWord(String word) {
    data.add(word);
    if (request.size() > 0) {
      request.clear();
    }
  }
  /**
   * Returns if the word is in the data structure. A word could contain the dot
   * character '.' to represent any one letter.
   */
  public boolean search(String word) {
    if (request.containsKey(word)) {
      return true;
    }
    boolean result = data.parallelStream().filter(str -> str.length() == word.length()).filter(str -> {
     return isEqualStr(word, str);
    }).findAny().isPresent();
    if (result) {
      request.put(word, result);
    }
    return result;
  }

  private boolean isEqualStr(String word, String data) {
    for (int i = 0; i < word.length(); i++) {
      if (word.charAt(i) == '.') {
        continue;
      }
      if (word.charAt(i) != data.charAt(i)) {
        return false;
      }
    }
    return true;
  }

}
