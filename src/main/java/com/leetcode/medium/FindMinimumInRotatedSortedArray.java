package com.leetcode.medium;

// https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
public class FindMinimumInRotatedSortedArray {
    public int findMin(int[] nums) {
        int start = 0, end = nums.length-1;
        if(nums == null || nums.length == 0) throw new IllegalArgumentException();

        while(start <= end){
            int mid = start + (end-start)/2;
            if(start == end) // Only one element, return it as the smallest.
                return nums[start];
                // If the middle element falls on the first increasing section and the rotation point falls on the right, only in that case move to the right.
            else if(nums[mid] >= nums[start] && nums[mid] >= nums[end])
                start = mid+1;
                // All other cases move to the left. Cases covered are, Non-rotated array, or 2nd section of the rotated array after the drop(min element) point.
            else
                end = mid;
        }
        return Integer.MIN_VALUE;
    }

}
