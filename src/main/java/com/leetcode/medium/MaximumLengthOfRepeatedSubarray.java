package com.leetcode.medium;

// https://leetcode.com/problems/maximum-length-of-repeated-subarray
public class MaximumLengthOfRepeatedSubarray {
    /*
        - O/p 0 if no match.

        Approach I:
        - Find all subarrays of length 1-n(O(N^2)) in the first array.
        - For each of subarray, try to match it in the 2nd array starting at each index.O(N^2)
        - Total Runtime: O(N^4).

        Approach II:
        - For each element i in array 1 - O(N))
            - See at what index j it matches in the array 2. O(N)
                - For k starting j, Keep on increasing the window by size 1 each time until it matches. Compare with the maximum and update if matches.
        - Total Runtime O(N^3).

        1, 2, 3, 2, 1
        3, 2, 1, 4, 7
    */
    public int findLength1(int[] A, int[] B) {
        int maxLen = Integer.MIN_VALUE;
        if(A == null || B == null || A.length == 0 || B.length ==0) return 0;
        for(int i=0; i<A.length; i++){
            for(int j=0; j< B.length; j++){
                int k = 0;
                if(A[i]== B[j]){
                    while(i+k < A.length && k+j < B.length && A[i+k] == B[j+k])
                        k++;
                }
                maxLen = Math.max(maxLen, k);
            }
        }
        return maxLen;
    }

    public int findLength(int[] A, int[] B) {
        int maxLen = 0;
        // if(A == null || B == null || A.length == 0 || B.length ==0) return 0;
        int[][] memo = new int[A.length+1][B.length+1];
        for(int i= A.length-1; i>=0 ;i--){
            for(int j=B.length-1;j>=0; j--){
                if(A[i] == B[j]){
                    memo[i][j] = 1+memo[i+1][j+1];
                    maxLen = Math.max(maxLen, memo[i][j]);
                }
            }
        }
        return maxLen;
    }

}
