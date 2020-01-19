package com.leetcode.hard;

import java.util.Arrays;

// https://leetcode.com/problems/edit-distance/
public class EditDistance {

    /*
    * Time: O(n*m) because of n*m subproblems and solving each one of them is constant time ignoring the recursion tree.
    * Space: O(n*m)
    * */
    public static int minDistanceBottomsUp(String word1, String word2) {
        int[][] memo = new int[word1.length()+1][word2.length()+1];
        int n = word1.length();
        int m = word2.length();
        for(int i=0; i < n; i++){
            memo[i][m] = n-i;
        }
        for(int j=0; j < m; j++){
            memo[n][j] = m-j;
        }
        for(int i=n-1; i>= 0; i--){
            for(int j=m-1; j>= 0; j--){
                if(word1.charAt(i) == word2.charAt(j))
                    memo[i][j] = memo[i+1][j+1];
                else {
                    memo[i][j] = 1 + Math.min(memo[i+1][j+1], Math.min(memo[i+1][j], memo[i][j+1]));
                }
            }
        }

        return memo[0][0];
    }

    public static void main(String[] args) {
        String str1 = "horse";
        String str2 = "ros";
        System.out.println(minDistanceBottomsUp(str1, str2));
    }

    public int minDistanceTopDown(String word1, String word2) {
        int[][] memo = new int[word1.length()][word2.length()];
        for(int[] rows : memo)
            Arrays.fill(rows,-1);
        return minOpsTopDown(word1, word2, 0,0,memo);
    }

    /*
     * Time: O(n*m) because of n*m subproblems and solving each one of them is constant time ignoring the recursion tree.
     * Space: O(n*m)
     * */
    private int minOpsTopDown(String word1, String word2, int i, int j, int[][] memo){
        // If First string has no characters, return the cost of adding all the remaining characters of second string.
        int n = word1.length();
        int m = word2.length();
        if(i == n ) return m-j;
        // If second string has no characters, return the cost of removing all the characters from the first string.
        if(j == m) return n-i;
        if(memo[i][j] != -1) return memo[i][j]; // Subproblems which are already solved.
        if(word1.charAt(i) == word2.charAt(j))
            memo[i][j] = minOpsTopDown(word1, word2,i+1, j+1,memo);
            // ith char in word1 isn't a match to jth character in word2.
        else {
            memo[i][j] = 1 + Math.min(Math.min(
                    minOpsTopDown(word1, word2, i+1, j+1,memo),
                    minOpsTopDown(word1, word2,i, j+1,memo)
                    ),
                    minOpsTopDown(word1, word2, i+1, j,memo));
        }
        return memo[i][j];
    }


    private int minOpsRecursive(String word1, String word2,int i, int j, int[][] memo){
        // If First string has no characters, return the cost of adding all the remaining characters of second string.
        int n = word1.length();
        int m = word2.length();
        if(i == n ) return m-j;
        // If second string has no characters, return the cost of removing all the characters from the first string.
        if(j == m) return n-i;
        if(word1.charAt(i) == word2.charAt(j))
            return minOpsTopDown(word1, word2,i+1, j+1,memo);
        // ith char in word1 isn't a match to jth character in word2.
        return 1 + Math.min(Math.min(
                minOpsTopDown(word1, word2, i+1, j+1,memo),
                minOpsTopDown(word1, word2,i, j+1,memo)
                ),
                minOpsTopDown(word1, word2, i+1, j,memo));
    }

}
