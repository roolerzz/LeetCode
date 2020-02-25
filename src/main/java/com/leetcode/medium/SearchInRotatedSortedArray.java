package com.leetcode.medium;

// https://leetcode.com/problems/search-in-rotated-sorted-array/
public class SearchInRotatedSortedArray {

    // Try to think about generic cases of how the progression would look like when the array would be rotate by 0 or more positions.
    // Ideally, numbers would increase for a while before being dropped(Lets call it section 1, Drop point would be the minimum number in the array) and
    // then would start to increase again(Lets call it Section 2 from drop point to the end).
    // Think about where can the middle element be. If the middle element is greater than the start, that means it belong to section 1.
    // else it belongs to section 2. Now within each section, compare the target element with the middle and then start/end
    // element to know where would the target exist in that specific section.
    //

    public int search(int[] nums, int target) {
        int start = 0, end = nums.length-1;
        while(start <= end){
            int mid = start + (end-start)/2;
            if(nums[mid] ==target)
                return mid;
            // Section 1.
            else if (nums[mid] >= nums[start]){
                // First/left half of section 1.(Reason for considering first half in the comnparison before the second half is the
                // availability of the start value to compare against.)
                if(target < nums[mid] && target >= nums[start]) end = mid-1;
                // second/right half of section 1.
                else start = mid+1;
            }
            // Section 2.
            else {
                // Right/second half of section 2.(Reason for doing 2nd half before first comparison is that we have the end value to compare against.)
                if(target > nums[mid] && target <= nums[end])
                    start = mid+1;
                // Left/first half of section 2.
                else
                    end = mid-1;
            }
        }
        return -1;
    }

}
