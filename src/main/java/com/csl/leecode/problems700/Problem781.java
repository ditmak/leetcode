package com.csl.leecode.problems700;

public class Problem781 {

  public int numRabbits(int[] answers) {
    int[] numbs = new int[1000];
    if (answers.length == 0) {
      return 0;
    }
    int sum = 0;
    for(int i : answers) {
      numbs[i] = numbs[i] + 1;
      if (numbs[i] % (i + 1) == 0) {
        sum += i + 1;
    }
    }
    return sum;
  }
}
