package com.leetcode.easy;

// https://leetcode.com/problems/maximum-subarray/
public class MaximumSubarray {

    /*
        Approach 1: Find all possible sub arrays (N^2) and find their sum in O(N).
        Approach 2: Imrpove #1 by getting rid of the repeat work. O(N^2).
        Approach 3: What is the maximum subarray sum ending at Index i?Linear solution O(N).
                    - Max of (
                        - a[i].
                        - maxSubArraySum[i-1]+ a[i]
                    )
        dp(:i) = Max (
                    dp(:i-1) + a[i]
                    a[i]
                    )
            Time: O(N)
            Space: O(N) implicit stack space.
    */

    private int maxSum;
    public int maxSubArray1(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        maxSum = nums[0];
        dp(nums, nums.length-1);
        return maxSum;
    }

    private int dp(int[] nums, int idx){
        if(idx==0)
            return nums[idx];
        int sum = Math.max(dp(nums, idx-1) + nums[idx], nums[idx]);
        maxSum = Math.max(sum, maxSum);
        return sum;
    }

    /*
      Greedy approach:
      - The problem to find max/min element(or sum) with a single array as the input is good candidate to be solved by greedy approach in linear time.
      - Idea is to pick the locally optimal move at each step that will lead to globally optimal solution.
      - So iterate over the array and at each step, update these things:
          - Current element.
          - currentmaxSum(at this point or until this element). Involves the use of currSum at previous step and the current element.
          - globalMax.
      TimeComplexity:
      Time: O(N)
      Space: O(1)
  */
    public int maxSubArray(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int currSum = nums[0], maxSum = nums[0];
        for(int i=1;i < nums.length; i++){
            currSum = Math.max(currSum+nums[i],nums[i]);
            maxSum = Math.max(currSum, maxSum);
        }
        return maxSum;
    }



    /*
        - Idea is Divide and Conquer. Given an array, find the largest subarray sum for the left sub array and for the right subarray and the cross sum, where elements from both sides are involved
        - Largest subarray sum for the current array would be the max of leftSum, rightSum and crossSum.
        TimeComplexity:
        Time: O(nlogN)
        Space: Implicit stack space(logN)
    */
    public int maxSubArray2(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        return recurse(nums, 0, nums.length-1);
    }

    private int recurse(int[] nums, int start, int end){
        if(start == end)
            return nums[start];
        int mid = start + (end-start)/2;
        int left = recurse(nums, start, mid);
        int right = recurse(nums, mid+1, end);
        int cross_sum = crossSum(nums, start, mid, end);
        return Math.max(left, Math.max(right, cross_sum));
    }

    private int crossSum(int[] nums, int start, int mid, int end){
        int leftSubSum = Integer.MIN_VALUE;
        int curr = 0;
        for(int i=mid; i>= start; i--){
            curr += nums[i];
            leftSubSum = Math.max(leftSubSum, curr);
        }
        int rightSubSum = Integer.MIN_VALUE;
        curr = 0;
        for(int i=mid+1; i<= end; i++){
            curr += nums[i];
            rightSubSum = Math.max(rightSubSum, curr);
        }
        return leftSubSum + rightSubSum;
    }


}
