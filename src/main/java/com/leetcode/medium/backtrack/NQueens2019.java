package com.leetcode.medium.backtrack;

// https://leetcode.com/problems/n-queens/
// https://leetcode.com/problems/n-queens-ii/
// https://www.geeksforgeeks.org/n-queen-problem-backtracking-3/
// Naive algorithm would be to generate all possible configurations of the queens on the board and
// print a configuration that satisfies the given constraints.
public class NQueens2019 {

    int counter = 0;

    public static void main(String[] args) {
        NQueens2019 q = new NQueens2019();
        q.placeNQueens(8);
    }

    // This should take time proportional to O(N^n) and space proportional to O(N^n) because of
    // implicit stack usage.
    public void placeNQueens(int n) {
        boolean[][] placed = new boolean[n][n];
        int[] ld = new int[2*placed.length-1];
        int[] rd = new int[2*placed.length-1];
        int[] colUsed = new int[placed.length];
        recPlaceNQueen(0, placed, ld, rd, colUsed);
    }

    private void recPlaceNQueen(int currRow, boolean[][] placed, int[] ld, int[] rd, int[] colUsed) {
        if (currRow >= placed.length) {
            // I am not going ahead in case I can't place an element for that particular row. Thus, in any
            // case it reaches this, point, I can safely assume that queens have been placed in a valid configuration.
            printBooleanMatrix(placed);
            return;
        }

        for (int j = 0; j < placed[0].length; j++) {
//            if (!intersects(placed, currRow, j)) {
            if (!optimizedIntersects(placed,currRow, j, ld, rd, colUsed)) {
                placeQueenAndUpdateState(placed, ld, rd, colUsed, currRow, j);
                recPlaceNQueen(currRow + 1, placed, ld,rd,colUsed);
                removeQueenAndUpdateState(placed, ld, rd, colUsed, currRow, j);
            }
        }
    }

    private void placeQueenAndUpdateState(boolean[][] placed, int[] ld, int[] rd, int[] colUsed, int row, int col){
        placed[row][col] = true;
        ld[row-col+placed.length-1]=1;
        rd[row+col]=1;
        colUsed[col]=1;
    }

    private void removeQueenAndUpdateState(boolean[][] placed, int[] ld, int[] rd, int[] colUsed, int row, int col){
        placed[row][col] = false;
        ld[row-col+placed.length-1]=0;
        rd[row+col]=0;
        colUsed[col]=0;
    }

    private void printBooleanMatrix(boolean[][] mat) {
        counter++;
        System.out.println("===================================================================");
        System.out.println("Printing " + counter + " valid configuration of the NQueens Problem.");

        for (int i = 0; i < mat.length; i++) {

            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j]) {
                    System.out.print(" X ");
                } else {
                    System.out.print(" 0 ");
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    private boolean intersects(boolean[][] placed, int row, int col) {
        // Check all the rows at jth col. Check Vertically for queen intersection.
        for (int i = 0; i < row; i++) {
            if (placed[i][col] == true) {
                return true;
            }
        }
        // North East diagonal
        for (int i = row, j = col; i >= 0 && j < placed[0].length; i--, j++) {
            if (placed[i][j] == true) {
                return true;
            }
        }
        // North West diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (placed[i][j] == true) {
                return true;
            }
        }
        return false;
    }


    // So instead of checking all the elements in the left Diagonal, Right diagonal and all the rows for that particular column,
    // this can be optimized by looking at the property of these diagonals.
    // 1. For element being placed at a specific(row,col), you only need to check if the column was occupied before.
    // 2. For NorthEast diagonal, The sum of (i+j) is always the same(and >0).
    // 3. For NorthWest diagonal, the difference of (i-j) is always the same. Except (i-j) could be negative for some combinations
    // of i and j. Thus we could normalize the dataset by adding N-1 so that things start at 0(we need to index the array using this value.)
    // Using these properties, we can use 3 1-d arrays to remember if the above situation happend. So validating if the current
    // positions intersects or not would only be a constant time operation(looking up in 3 arrays.)
    private boolean optimizedIntersects(boolean[][] placed, int row, int col, int[] ld, int[] rd, int[] colUsed) {
        // Check all the rows at jth col. Check Vertically for queen intersection.
        return colUsed[col]==1 || ld[row-col+placed.length-1] == 1 || rd[row+col] ==1;
    }
}
