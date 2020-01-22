package com.leetcode.medium;

import java.util.Arrays;

public class LongestIncreasingSubsequence {

    // Iterative solution. Time complexity (O(N^2) classic double for loop setting.)
    // Space: O(N) for the dp table.
    public int lengthOfLIS2(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        int res = 0;
        Arrays.fill(dp, 1);
        for(int i=0; i < nums.length; i++){
            for(int j=0; j < i; j++){
                if(nums[j] < nums[i]){
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /*
    Idea: Length of longest increasing sub-sequence including element upto and including i could be
    1 + LIS(j) if arr[i] > arr[j] for j from i-1 to 0.
    Recursive solution. Time complexity (O(N^2))
     Space: O(N) for the dp table.
    */
    public int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        return 0;
    }



}
