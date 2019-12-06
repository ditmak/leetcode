package com.csl.leecode.problems100;

/**
 * 这题需要动态规划的思想在里面,如果直接计算最后结果,会十分困难,借助动态规划的思想, 可以将问题拆分成粒度更小的问题然后通过递推的方式,获取最后的结果
 * 正向计算,容易陷入贪心算法,得不到最优解,先算出来的结果不一定合适下一步的情况 反向计算 可以得出最优解
 * 
 * 正向计算很容易陷入先当前值,然后推导出下一个值的情况,这个时候要考虑根据后面的数值推导出当前结果.
 * 
 * @author chensongle
 *
 */
public class Problem174 {

  public static void main(String[] args) {
    // int[][] dungeon = { { -2, -4, 3 }, { -5, -10, 1 }, { 10, 30, -5 } };
    // int[][] dungeon = { { 100 } };
    int[][] dungeon = { { 1, -3, 3 }, { 0, -2, 0 }, { -3, -3, -3 } };
    Problem174 problem174 = new Problem174();
    int result = problem174.calculateMinimumHP(dungeon);
    System.out.println(result);
  }

  public int calculateMinimumHP2(int[][] dungeon) {
    int row = dungeon.length;
    int col = dungeon[0].length;
    int blood[][] = new int[row][col];
    int lastDungeon = dungeon[row - 1][col - 1];
    if (lastDungeon >= 0) {
      blood[row - 1][col - 1] = 1;
    } else {
      blood[row - 1][col - 1] = 1 - lastDungeon;
    }
    for (int x = row - 2; x >= 0; x--) {
      blood[x][col - 1] = minus(blood[x + 1][col - 1], dungeon[x][col - 1]);
    }
    for (int y = col - 2; y >= 0; y--) {
      blood[row - 1][y] = minus(blood[row - 1][y + 1], dungeon[row - 1][y]);
    }
    for (int x = row - 2; x >= 0; x--) {
      for (int y = col - 2; y >= 0; y--) {
        int right = minus(blood[x][y + 1], dungeon[x][y]);
        int down = minus(blood[x + 1][y], dungeon[x][y]);
        blood[x][y] = right > down ? down : right;
      }
    }
    return blood[0][0];
  }

  public int minus(int x, int y) {
    return x - y > 0 ? x - y : 1;
  }

  public int calculateMinimumHP(int[][] dungeon) {
    int row = dungeon.length;
    int col = dungeon[0].length;
    HealthNode[][] nodes = new HealthNode[row][col];
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        int change = dungeon[i][j];
        HealthNode leftNode = j == 0 ? null : nodes[i][j - 1];
        HealthNode upNode = i == 0 ? null : nodes[i - 1][j];
        HealthNode selectNode = new HealthNode(1, 1);
        if (leftNode == null && upNode == null) {
          selectNode = selectNode.getNew(change);
        } else if (leftNode == null) {
          selectNode = upNode.getNew(change);
        } else if (upNode == null) {
          selectNode = leftNode.getNew(change);
        } else {
          HealthNode new1 = upNode.getNew(change);
          HealthNode new2 = leftNode.getNew(change);
          if (new1.minHealth < new2.minHealth) {
            selectNode = new1;
          } else if (new1.minHealth == new2.minHealth) {
            if (new1.currentHealth > new2.currentHealth) {
              selectNode = new1;
            } else {
              selectNode = new2;
            }
          } else {
            selectNode = new2;
          }
        }
        nodes[i][j] = selectNode;
      }
    }

    return nodes[row - 1][col - 1].minHealth;
  }

  class HealthNode {
    public int minHealth;
    public int currentHealth;

    public HealthNode(int minHealth, int currentHealth) {
      this.minHealth = minHealth;
      this.currentHealth = currentHealth;
    }

    public HealthNode getNew(int change) {
      int newLeft = currentHealth + change;
      if (newLeft > 0) {
          return new HealthNode(minHealth, newLeft);
      } else {
        return new HealthNode(minHealth - newLeft + 1, 1);
      }
    }
  }

}
