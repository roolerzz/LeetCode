package com.leetcode.medium;

// https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
// Related TotalOccurencesOfAnElementInSortedArray and FirstOccurenceOfElementInSortedArray.
public class FindFirstAndLastIndexOfElementInSortedArray {

    /*
        - Find the first occurence of the given number.
            - Possible values can be

            - 0-N-1 (Can be valid/invalid depending upon if the element exists(or doesn't) at that index).
            - N If the given number is greater than the last element
        - If not a valid return [-1,-1]
        - If valid, find the first index of next greater element(target+1) or the position that should be inserted to.
        - return {equalIdx, endIdx-1};
    */

        public int[] searchRange(int[] nums, int target) {
            if(nums == null || nums.length == 0) return new int[]{-1,-1};
            int start = findFirstOccurence(nums, target);
            if(start == nums.length || nums[start] != target) return new int[]{-1,-1};
            int startNext = findFirstOccurence(nums,target+1);
            return new int[]{start, startNext-1};
        }

        private int findFirstOccurence(int[] nums, int target){
            int l = 0, r = nums.length;
            while(l < r){
                int mid = l + ((r-l)>>1);
                if(nums[mid] < target) l = mid+1;
                else if (nums[mid] >= target) r=mid;
            }
            return l;
        }


}
