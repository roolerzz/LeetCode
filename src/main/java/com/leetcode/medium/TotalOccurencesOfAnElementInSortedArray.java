package com.leetcode.medium;

public class TotalOccurencesOfAnElementInSortedArray {

    private int findFirstOccurence(int[] nums, int target){
        int l = 0, r = nums.length;
        while(l < r) {
            int mid = l + ((r-l)>>1);
            if(nums[mid] < target) l = mid+1;
            else if (nums[mid] >= target) r = mid;
        }
        return l;
    }

    public int totalOccurences(int[] nums, int target){
        if(nums == null || nums.length == 0) return 0;
        int leftMost = findFirstOccurence(nums,target);
        if(leftMost == nums.length || nums[leftMost] != target) return 0;
        int rightMost = findFirstOccurence(nums,target+1)-1;
        return rightMost-leftMost+1;
    }

  public static void main(String[] args) {
    //
      TotalOccurencesOfAnElementInSortedArray t1 = new TotalOccurencesOfAnElementInSortedArray();
      int[] nums = {1,1,1,2,2,3,3,3,3,3,3,4,5,10};
      System.out.println(t1.totalOccurences(nums, 10));

  }
}
