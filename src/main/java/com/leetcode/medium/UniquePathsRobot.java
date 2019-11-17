package com.leetcode.medium;

import java.util.Random;

// https://leetcode.com/problems/unique-paths/
public class UniquePathsRobot<T> {

    public int bruteForcePaths(int m, int n){
        if (m < 0 || n < 0) return 0;
        return recBruteForcePaths(m-1,n-1, m-1, n-1);
    }

    private int recBruteForcePaths(int currX, int currY, int maxX, int maxY) {
        if(!isValid(currX,currY, maxX, maxY))
            return 0;
        if(isBaseLocation(currX,currY))
            return 1;
        counter++;
        return recBruteForcePaths(currX-1,currY, maxX, maxY) + recBruteForcePaths(currX,currY-1, maxX, maxY);
    }

    public int bruteForcePathsMemoization(int m, int n){
        if (m < 0 || n < 0) return 0;
        int[][] pathSum = new int[m][n];
        pathSum[0][0]=1;
        return recBruteForcePathsMemoizations(m-1,n-1, m-1, n-1, pathSum);
    }

    private static int counter = 0;
    private static int counter2 = 0;

    private int recBruteForcePathsMemoizations(int currX, int currY, int maxX, int maxY, int[][] pathSum) {
        if(!isValid(currX,currY, maxX, maxY))
            return 0;
        if(resultAlreadyComputed(currX, currY, pathSum))
        {
            return pathSum[currX][currY];
        }
        counter2++;
        pathSum[currX][currY] = recBruteForcePathsMemoizations(currX-1,currY, maxX, maxY, pathSum) + recBruteForcePathsMemoizations(currX,currY-1, maxX, maxY, pathSum);
        return pathSum[currX][currY];
    }

    private boolean resultAlreadyComputed(int currX, int currY, int[][] pathSum){
        return pathSum[currX][currY] != 0;
    }

    private boolean isValid(int currX, int currY, int maxX, int maxY){
        return currX >= 0 && currY >= 0 && currX <= maxX && currY <= maxY;
    }

    private boolean isBaseLocation(int currX, int currY) {
        return currX == 0 && currY == 0;
    }

    public int iterativePaths(int m, int n, boolean[][] blocker) {
        if (m <= 0 || n <= 0 ) return -1;
        int[][] pathSum = new int[m][n];
        fillFirstRow(pathSum, n,blocker);
        fillFirstCol(pathSum, m,blocker);
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n ; j++) {
                if(!isBlocked(blocker, i,j)){
//                    System.out.print("Grid " + i +":" + j+" is not blocked.");
                    pathSum[i][j] = pathSum[i-1][j] + pathSum[i][j-1];
                }
            }
        }
        return pathSum[m-1][n-1];
    }

    private boolean isBlocked(boolean[][] blocker, int row, int col) {
        return blocker[row][col];
    }

    private void fillFirstRow(int[][] pathSum, int cols, boolean[][] blocker) {
        for (int j = 0 ; j < cols ; j++)
            if(!isBlocked(blocker, 0, j))
                pathSum[0][j] = 1;
    }
    private void fillFirstCol(int[][] pathSum, int rows, boolean[][] blocker) {
        for (int i = 0 ; i < rows ; i++)
            if(!isBlocked(blocker, i,0 ))
                pathSum[i][0] = 1;
    }

    class Pair{
        int i;
        int j;
        Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    private void fillRandomly(boolean[][] blockers, int noOfBlockers) {
        int m = blockers.length;
        int n = blockers[0].length;
        int totalElements = m * n;
//        System.out.println("Number of blockers to be filled are: " + noOfBlockers);
        if (noOfBlockers > totalElements ) return;
        for(int i = 0 ; i < noOfBlockers; i++) {
            Pair p = generateRandomPair(m,n);
            blockers[p.i][p.j] = true;
        }
    }

    Random generator = new Random();

    private Pair generateRandomPair(int row, int col) {
        int r = generator.nextInt(row);
        int c = generator.nextInt(col);
        System.out.println("Generated pair is - " + r + ":" + c);
        return new Pair(r,c);
    }

  public static void main(String[] args) {
    UniquePathsRobot robot = new UniquePathsRobot();
      int m = 10, n = 10;
      boolean[][] blockers = new boolean[m][n];
//      int noOfBlockers = m*n;
      int noOfBlockers = 10000;
      robot.fillRandomly(blockers, noOfBlockers);
      robot.print2DArrayBoolean(blockers);
      int paths =robot.bruteForcePaths(m, n);
      System.out.println("Recursive brute force paths to m : " + m + " and n : " + n + " are : " + paths + " paths. using : "
              + counter +
              " function calls.");
      int paths2 = robot.bruteForcePathsMemoization(m,n);
        System.out.println("Recursive paths with memoization to m : " + m + " and n : " + n + " are : " + paths2 + " paths. using : "
                + counter2 +
                " function calls.");
        int paths3 = robot.iterativePaths(m,n, blockers);
        System.out.println("Iterative paths for m : " + m + " and n : " + n + " with: " + noOfBlockers +" blockers are : " + paths3 + " paths." );
  }

    private void print2DArrayInts(int[][] arr) {
        int rows = arr.length;
        int cols = arr[0].length;
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols ; col++) {
                System.out.print(arr[row][col]);
                System.out.print("--->");
            }
            System.out.println();
        }
    }

    private void print2DArrayBoolean(boolean[][] arr) {
        int rows = arr.length;
        int cols = arr[0].length;
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols ; col++) {
                System.out.print(arr[row][col]);
                System.out.print("--->");
            }
            System.out.println();
        }
    }
}
