package com.leetcode.easy;

// https://leetcode.com/problems/move-zeroes/
public class MoveZeroes {
    /*
    - Initialize ins=0, curr=0.
    - while(curr < nums.length){
        if(nums[curr] !=0)
            nums[ins++] = nums[curr];
        curr++;
    - while(ins < nums.length)
        nums[ins++]=0;
    [1,3,12,0,0]
    - Validations.
        Null/empty array? stop processing.
    -
    }

    */

        public void moveZeroes(int[] nums) {
            if(nums == null || nums.length==0) return;
            int ins=0, curr=0;
            while(curr < nums.length){
                if(nums[curr] != 0)
                    nums[ins++] = nums[curr];
                curr++;
            }
            while(ins < nums.length)
                nums[ins++]=0;

        }
}
