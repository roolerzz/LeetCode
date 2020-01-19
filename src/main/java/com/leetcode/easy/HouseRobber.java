package com.leetcode.easy;

import java.util.Arrays;

// https://leetcode.com/problems/house-robber/
public class HouseRobber {
    /*
    // Idea: Think about maximum amount of money we can make till ith house. Well that could either be Max money we made until the last house(and not robbing the current one) or
    the Max money we made till the second to last house(+ robbing the current house).
    */

        public int rob(int[] nums) {
            if(nums == null || nums.length == 0)
                return 0;
            // if(nums.length == 1) return nums[0];
            // if(nums.length == 2) return Math.max(nums[0],nums[1]);
            int a = 0, b = 0;
            for(int elem : nums){
                int temp = Math.max(a + elem, b);
                a = b;
                b = temp;
            }
            return b;
        }



        public int robBottomUp(int[] nums) {
            if(nums == null || nums.length == 0)
                return 0;
            if(nums.length == 1) return nums[0];
            if(nums.length == 2) return Math.max(nums[0],nums[1]);
            int[] memo = new int[nums.length];
            memo[0] = nums[0];
            memo[1]=Math.max(nums[0],nums[1]);
            for(int i=2; i < nums.length; i++){
                memo[i] = Math.max(memo[i-2] + nums[i],memo[i-1]);
            }
            return memo[nums.length-1];
        }



        public int robTopDown(int[] nums) {
            if(nums == null || nums.length == 0)
                return 0;
            int[] memo = new int[nums.length];
            Arrays.fill(memo,-1);
            memo[0]=nums[0];
            if(nums.length > 1)
                memo[1] = Math.max(nums[0],nums[1]);
            return moneyTopDown(nums, nums.length-1, memo);
        }

        private int moneyTopDown(int[] nums, int i, int[] memo){
            if(memo[i] != -1) return memo[i];
            memo[i] = Math.max(moneyTopDown(nums, i-1, memo), moneyTopDown(nums, i-2,memo)+nums[i]);
            return memo[i];
        }

        public int robRecursive(int[] nums) {
            if(nums == null || nums.length == 0)
                return 0;
            return moneyRecursive(nums, nums.length-1);
        }

        private int moneyRecursive(int[] nums, int i){
            if(i < 0) return 0;
            int money = Math.max(moneyRecursive(nums, i-1), moneyRecursive(nums, i-2)+nums[i]);
            return money;
        }


}
