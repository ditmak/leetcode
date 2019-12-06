package com.csl.leecode.problems100;

public class Problem122 {
  public int maxProfit(int[] prices) {
    int profit = 0;
    if (prices.length == 0) {
      return 0;
    }
    for (int i = 0; i + 1 < prices.length; i++) {
      if (prices[i] - prices[i + 1] <= 0) {
        profit += prices[i + 1] - prices[i];
      }
    }
    return profit;

  }
}
