package com.companyspecific.Doordash;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class LargetContiguousBlock {


/*
        @param matrix, a 2-d array (list of lists) of integers @return the size of the largest contiguous block (horizontally/vertically connected) of numbers with the same value */

        private int maxSize;

        public int largestContinuousBlockRec(int [][] matrix){
/*
            - Create a set for rememberihng visited indexs.
                    - For each unvisited node i - 0 n-1 j0- m-1
                    - dfs(node) mark visited if same.
                    - maxSize updated across different sizes of connected components. */
            if(matrix == null || matrix.length ==0) return 0;

            Set<String> visited = new HashSet<>(); // m * n 0,0 m,n  O (m + n) (space for implicit stack) O(m*n)
// 100 * 100 = 10000
            for(int row=0; row < matrix.length; row++) {
                for(int col = 0 ; col < matrix[row].length;col++){
                    if(isValid(matrix, visited, row, col, matrix[row][col])){
                        int size =  dfs(matrix, visited, row, col, matrix[row][col]);
                        maxSize = Math.max(size, maxSize);
                    }
                }
            }
            return maxSize;
// return -1;
        }

        public int largestContinuousBlock(int [][] matrix){
/*
- Create a set for rememberihng visited indexs.
- For each unvisited node i - 0 n-1 j0- m-1
    - dfs(node) mark visited if same.
- maxSize updated across different sizes of connected components.
*/
            if(matrix == null || matrix.length ==0) return 0;

            Set<String> visited = new HashSet<>(); // m * n 0,0 m,n  O (m + n) (space for implicit stack) O(m*n)
            Deque<Integer> rows = new ArrayDeque<>();
            Deque<Integer> cols = new ArrayDeque<>();
            int maxSizeItr = 0;
// 100 * 100 = 10000
            for(int row=0; row < matrix.length; row++) {
                for(int col = 0 ; col < matrix[row].length;col++){
                    if(isValid(matrix, visited, row, col, matrix[row][col])){
                        int val = matrix[row][col];
                        int size= 0;
                        rows.push(row);
                        cols.push(col);

                        while(!rows.isEmpty()){
                            int currR = rows.pop();
                            int currC = cols.pop();
                            visited.add(currR + ":" + currC);
                            if(isValid(matrix, visited, currR, currC,val)){
                                size++;
                                addToStacks(rows, cols, currR, currC);
                            }

                        }
                        System.out.println("Size is : "+size);
                        System.out.println("Max Size is : " + maxSizeItr);
                        maxSizeItr = Math.max(size, maxSizeItr);

                    }
                }
            }
            return maxSizeItr;
// return -1;
        }

        private void addToStacks(Deque<Integer> rows, Deque<Integer> cols, int row, int col){
            rows.add(row+1);
            cols.add(col);

            rows.add(row-1);
            cols.add(col);

            rows.add(row);
            cols.add(col+1);

            rows.add(row);
            cols.add(col-1);
        }
        /*
        {1,2,3},
        {4,1,6},
        {4,5,1}

          {1,1,1,2,4},
          {5,1,5,3,1},
          {3,4,2,1,1}

        */
        private int dfs(int[][] matrix, Set<String> visited, int row, int col, int val){
            if(!isValid(matrix, visited, row, col, val)) return 0;
            visited.add(row + ":" + col);
            // return 1 + dfs(matrix, visited, row+1, col, val) + dfs(matrix, visited, row-1, col, val) + dfs(matrix, visited, row, col+1, val) + dfs(matrix, visited, row, col-1, val);
            return 1 + dfs(matrix, visited, row+1, col, val) + dfs(matrix, visited, row-1, col, val) + dfs(matrix, visited, row, col+1, val) + dfs(matrix, visited, row, col-1, val)
                    // Diagonal below
                    + dfs(matrix, visited, row+1, col+1, val) + dfs(matrix, visited, row+1, col-1, val) + dfs(matrix, visited, row-1, col+1, val) + dfs(matrix, visited, row-1, col-1, val);
        }

        private boolean isValid(int[][] matrix, Set<String> visited, int row, int col, int val){
            return row>=0 && row < matrix.length && col >=0 && col < matrix[row].length  && !visited.contains(row + ":" + col) && matrix[row][col] == val;
        }
        public static void main(String[] args) {
            LargetContiguousBlock s = new LargetContiguousBlock();

            int expected1 = 2;
            int [][] matrix1 = new int[][]{
                    {1,2,3},
                    {4,1,6},
                    {4,5,1}
            };

            int expected2 = 4;
            int [][] matrix2 = new int[][]{
                    {1,1,1,2,4},
                    {5,1,5,3,1},
                    {3,4,2,1,1}
            };

            int expected3 = 11;
            int [][] matrix3 = new int[][]{
                    {3,3,3,3,3,1},
                    {3,4,4,4,3,4},
                    {2,4,3,3,3,4},
                    {2,4,4,4,4,4}
            };

            int expected4 = 24;
            int [][] matrix4 = new int[][]{
                    {0,0,0,0,0},
                    {0,0,0,0,0},
                    {0,0,1,0,0},
                    {0,0,0,0,0},
                    {0,0,0,0,0}
            };



            s.runTestCase(matrix1,expected1);
            s.runTestCase(matrix2,expected2);
            s.runTestCase(matrix3,expected3);
            s.runTestCase(matrix4,expected4);
        }

        public void runTestCase(int [][] matrix, int expected){
            int result = largestContinuousBlock(matrix);
            if(result != expected){
                System.out.println("\n--Failed--");
                printMatrix(matrix);
                System.out.print("Expected: ");
                System.out.println(expected);
                System.out.print("Actual: ");
                System.out.println(result);
            } else {
                System.out.println("--PASSED--");
            }

        }

        private static void printMatrix(int [][] matrix){
            for (int i = 0 ; i < matrix.length ; i++){
                System.out.print('[');
                for (int j = 0 ; j < matrix[0].length ; j++){
                    System.out.print(matrix[i][j]);
                }
                System.out.println(']');
            }
            System.out.println();
        }

}
