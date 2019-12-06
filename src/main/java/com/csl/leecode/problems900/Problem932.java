package com.csl.leecode.problems900;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem932 {
  /**
   * 
   * For some fixed N, an array A is beautiful if it is a permutation of the
   * integers 1, 2, ..., N, such that:
   * 
   * For every i < j, there is no k with i < k < j such that A[k] * 2 = A[i] +
   * A[j].
   * 
   * Given N, return any beautiful array A. (It is guaranteed that one exists.)
   * 
   * 
   * 
   * Example 1:
   * 
   * Input: 4 Output: [2,1,4,3] Example 2:
   * 
   * Input: 5 Output: [3,1,2,5,4]
   * 
   * 
   * Note:
   * 
   * 1 <= N <= 1000
   */
  public static void main(String[] args) {
    Solution s = new Solution();
    System.out.println(s.beautifulArray(4));
  }

}

class Solution {

  Map<Integer, int[]> memo;

  public int[] beautifulArray3(int N) {
    memo = new HashMap<>();
    return f(N);
  }

  public int[] f(int N) {
    if (memo.containsKey(N))
      return memo.get(N);

    int[] ans = new int[N];
    if (N == 1) {
      ans[0] = 1;
    } else {
      int t = 0;
      for (int x : f((N + 1) / 2)) // odds
        ans[t++] = 2 * x - 1;
      for (int x : f(N / 2)) // evens
        ans[t++] = 2 * x;
    }
    memo.put(N, ans);
    return ans;
  }

  public int[] beautifulArray1(int N) {
    List<Integer> result = new ArrayList<>();
    result.add(1);
    while (result.size() < N) {
      List<Integer> tmp = new ArrayList<>();
      for (int i : result)
        if (i * 2 - 1 <= N)
          tmp.add(i * 2 - 1);
      for (int i : result)
        if (i * 2 <= N)
          tmp.add(i * 2);
      result = tmp;
    }
    return result.stream().mapToInt(i -> i).toArray();
  }
  public int[] beautifulArray(int N) {

    if (N == 1) {
      return new int[] { 1 };
    } else {
      if (N % 2 == 0) {
        int left[] = oddNumbs(beautifulArray(N / 2));
        int right[] = evenNumbs(beautifulArray(N / 2));
        return joinArray(left, right);
      } else {
        int left[] = oddNumbs(beautifulArray(N / 2 + 1));
        int right[] = evenNumbs(beautifulArray(N / 2));
        return joinArray(left, right);
      }
    }
  }

  public int[] oddNumbs(int data[]) {
    return Arrays.stream(data).map(i -> (2 * i - 1)).toArray();
  }

  public int[] evenNumbs(int data[]) {
    return Arrays.stream(data).map(i -> (2 * i)).toArray();
  }

  public int[] joinArray(int left[], int right[]) {
    int length = left.length + right.length;
    int[] result = new int[length];
    System.arraycopy(left, 0, result, 0, left.length);
    System.arraycopy(right, 0, result, left.length, right.length);
    return result;
  }
}
