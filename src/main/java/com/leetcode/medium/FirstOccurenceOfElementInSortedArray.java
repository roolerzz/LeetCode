package com.leetcode.medium;

public class FirstOccurenceOfElementInSortedArray {

  public static void main(String[] args) {
      //

      FirstOccurenceOfElementInSortedArray f1 = new FirstOccurenceOfElementInSortedArray();
      int[] nums = {1,1,1,2,2,3,3,3,3,3,5};
      System.out.println(f1.findFirstOccurence(nums, 0));
  }

  // Returns -1 if the element doesn't exist in the array. You could use a different approach and return the index where
  // that element might have been present at its first location if it was in the array.
    private int findFirstOccurence(int[] nums, int target){
        int l = 0, r = nums.length;
        while(l < r){
            int mid = l + ((r-l)>>1);
            if(nums[mid] < target) l = mid+1;
            else if (nums[mid] >= target) r=mid;
        }
        return l == nums.length || nums[l]!=target?-1:l;
    }

}
