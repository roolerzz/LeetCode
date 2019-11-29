package com.leetcode.medium;

import java.util.Arrays;

// https://leetcode.com/problems/product-of-array-except-self/
public class ProductOfArrayExceptSelf {

        public int[] productExceptSelf(int[] nums) {
            if(nums == null || nums.length <= 1) return nums;
            int[] res = new int[nums.length];
            Arrays.fill(res,1);
            int left =1, prev =1, right = 1;

            for(int i=0;i < nums.length; i++){
                left *= prev;
                prev = nums[i];
                res[i] *= left;
            }
            prev = 1;
            for(int j=nums.length-1; j>=0; j--){
                right *= prev;
                prev = nums[j];
                res[j] *= right;
            }

            return res;
        }
        // My weird solution 1. Works anyhow.
        public int[] productExceptSelfWithDivisionHandlingZeroes(int[] nums) {
            if (nums == null || nums.length <= 1) return nums;

            int last = 1, pl = 1, pr =1, prz = 1, zeroCount = 0;

            for(int i = 0 ; i < nums.length; i++){
                pr *= nums[i];
                if(nums[i] == 0){
                    zeroCount++;
                }
                else {
                    prz *= nums[i];
                }
            }

            if(zeroCount > 1) {
                Arrays.fill(nums,0);
                return nums;
            }
            for(int i = 0; i< nums.length; i++){
                pl *= last;
                last = nums[i];
                if(nums[i]!=0){
                    pr /= nums[i];
                    prz /= nums[i];
                    nums[i] = pr;
                }
                else
                    nums[i]=prz;
                nums[i] *= pl;
            }
            return nums;
        }
}
