package com.leetcode.medium;

import java.util.Arrays;

// https://leetcode.com/problems/longest-common-subsequence/
public class LongestCommonSubsequence {
    /*
    Brute force:
    - Find all the subsequences of A(2^n) and B(2^m). Check for comparison amongst pair made by elements from each group. Time to compare each = O(MIN(M,N))
    Total runtime : 2^n*2^m*Min(M,N).

    - Longest common subsquence starting at idx i from String 1 and index j from String 2 is:
        - if(i == j):
            - 1 + LCS(i+1,j+1);
        - if(i != j):
            Maximum of:
            - LCS(i+1, j)
            - LCS(i,j+1)
            - LCS(i+1,j+1)
        LCS(n,m) = 0 if either of i ==n or j == m; If either of string is empty.
    */

    public int longestCommonSubsequenceBruteForce(String text1, String text2) {
        if(text1 == null || text2 == null) return 0;
        return lcsRecBruteForce(text1, text2, 0, 0);
    }

    private int lcsRecBruteForce(String text1, String text2, int i, int j){
        int n = text1.length();
        int m = text2.length();
        if(i == n || j == m) return 0;
        if(text1.charAt(i) == text2.charAt(j))
            return 1 + lcsRecBruteForce(text1,text2,i+1,j+1);
        return Math.max(lcsRecBruteForce(text1, text2, i+1,j+1), Math.max(lcsRecBruteForce(text1,text2,i+1,j),                                                       lcsRecBruteForce(text1,text2,i,j+1)));
    }

    // Reduced to Linear Space.
    public int longestCommonSubsequence(String text1, String text2) {
        if(text1 == null || text2 == null) return 0;
        int[] rowMemo = new int[text2.length()+1];
        int n = text1.length();
        int m = text2.length();
        for(int i=n-1; i>=0; i--){
            int prev = 0;
            for(int j=m-1; j>=0; j--){
                int temp = rowMemo[j];
                if(text1.charAt(i) == text2.charAt(j)){
                    rowMemo[j] = 1 + prev;
                }
                else
                    rowMemo[j] = Math.max(rowMemo[j+1], rowMemo[j]);
                prev = temp;
            }
        }
        return rowMemo[0];
    }

    public int longestCommonSubsequenceBottomUp(String text1, String text2) {
        if(text1 == null || text2 == null) return 0;
        int[][] memo = new int[text1.length()+1][text2.length()+1];
        int n = text1.length();
        int m = text2.length();
        // for(int i=0 ; i < n ; i++){
        //     memo[i][m]=0;
        // }
        // for(int j=0 ; j < m ; j++){
        //     memo[n][j]=0;
        // }

        for(int i=n-1; i>=0; i--){
            for(int j=m-1; j>=0; j--){
                if(i == 0 || j == 0)
                    memo[i][j]=0;
                else if(text1.charAt(i) == text2.charAt(j)){
                    memo[i][j] = 1 + memo[i+1][j+1];
                }
                else
                    memo[i][j] = Math.max(memo[i+1][j+1], Math.max(memo[i+1][j], memo[i][j+1]));
            }
        }
        return memo[0][0];
    }


    /*
    Time : O(m*n) m*n subproblem where each problem gets solved only once. Other than branching cost(which would be constant due to memoization) there is constant time work in each subproblem.
    Space: O(m*n) for each implicit stack and the memo table.
    */
    public int longestCommonSubsequenceTopDown(String text1, String text2) {
        if(text1 == null || text2 == null) return 0;
        int[][] memo = new int[text1.length()][text2.length()];
        for(int[] row: memo)
            Arrays.fill(row, -1);
        return lcsTopDown(text1, text2, 0, 0, memo);
    }

    private int lcsTopDown(String text1, String text2, int i, int j, int[][] memo){
        if(i == text1.length() || j == text2.length()) return 0;
        if(memo[i][j] != -1) return memo[i][j];
        if(text1.charAt(i) == text2.charAt(j)){
            memo[i][j] = 1 + lcsTopDown(text1,text2,i+1,j+1, memo);
        }
        else {
            memo[i][j] = Math.max(lcsTopDown(text1, text2, i+1,j+1,memo), Math.max(lcsTopDown(text1,text2,i+1,j,memo),                                                       lcsTopDown(text1,text2,i,j+1,memo)));
        }

        return memo[i][j];
    }


}
