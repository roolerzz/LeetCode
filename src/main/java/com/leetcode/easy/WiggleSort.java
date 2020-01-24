package com.leetcode.easy;

import java.util.Arrays;

// https://leetcode.com/problems/wiggle-sort/
public class WiggleSort {
    /*
        1, 2, 3, 4, 5, 6

        1, 3, 2, 5, 4, 6

        - Generate all possible permutations of the numbers and evauate all of them if they satisfy the criteria. O(n!*n). Too bad.
        - 1, 6, 2, 5, 3, 4


        1 2 20 21 40 100

        1, 100, 2, 40 20 21

    - Create a duplicate aux array and sort that.
    - Starting 2 pointers from the ends, take first element from the left, then second from the right and so on.

    */

        public void wiggleSort1(int[] nums) {
            int[] copy = Arrays.copyOfRange(nums, 0, nums.length);
            Arrays.sort(copy);
            int left = 0, right = copy.length-1;
            int count=0;
            while(count < nums.length){
                if(count%2 == 0)
                    nums[count++] = copy[left++];
                else
                    nums[count++]=copy[right--];
            }
        }


        public void wiggleSort(int[] nums) {
            for(int i=0; i < nums.length-1; i++){
                if((i%2 == 0 && nums[i] > nums[i+1]) ||(i%2 != 0 && nums[i]<nums[i+1]))
                    swap(nums, i, i+1);
            }
        }

        public void wiggleSort2(int[] nums) {
            Arrays.sort(nums);
            for(int i=1; i<nums.length-1; i+=2)
                swap(nums, i, i+1);
        }
        private void swap(int[] nums, int i, int j){
            int temp = nums[i];
            nums[i]=nums[j];
            nums[j] = temp;
        }

}
