package com.leetcode.medium;

// https://leetcode.com/problems/next-permutation
public class NextPermutations {
    /*
    1,2,3 -> 1*100 + 2*10+ 3
    1,2,6,8,7,4 -> 1*100 + 5*10 + 4
    1,2,6,4,7,8 // small
    1,2,6,4,8,7 // small
    1,2,6,7,8,4 // small
    1,2,6,8,7,4 //
    1,2,4,8,7,6 // small
    1,2,7,8,6,4 // bigger
    1,2,7,6,8,4 // bigger
    1,2,7,4,6,8 // bigger // next bigger.
    I/P:
    - How exactly is my input given? Number, String, sequence of digits?
    - Null/empty array?
    - How big can the full number be. Would it fit into the int memory.
    - duplicates? sure.
    1) Brute force. Make all possible permutation, and find the one with lowest difference. O(n!) permutations.

    2) Start from the very far right. Find the index where the drop is happening while going from right. if a[i] > a[i+1] i--; else break;
       - Swap a[i] with the next bigger element from what we have seen before.
           - How do you find which elements from the ones that we have seen till now,
           would be next bigger element than the drop.
           - 2 for loops, 1 to find drop, and 1 to find next big one.
       - Now sort the numbers from A[i+1 .. n).
    */

        public void nextPermutation(int[] nums) {
            if(nums == null || nums.length < 2) return;
            int i = nums.length-2;
            while(i >=0 && nums[i+1] <= nums[i]) {
                i--;
            }
            // if(i < 0) {
            //    Arrays.sort(nums); return;
            //}

            // This is redundant. If you think about it the number on the right of i are
            // already in the descending order, and we need to swap a[i]  with the first number that is greater than that. So starting from right to left, you will encounter few smaller elements, until the first number bigger than a[i]. Swap with that.
            // int aI = nums[i];
            // int nextBiggerIdx = i;
            // int minDiff = Integer.MAX_VALUE;
            // for (int j = i+1;j<nums.length && nums[j] != aI; j++){
            //     int diff = nums[j]-nums[i];
            //     if(diff >0 && diff < minDiff){
            //         minDiff = diff;
            //         nextBiggerIdx = j;
            //     }
            // }
            if(i >=0){
                int j = nums.length-1;
                while(j >= i+1 && nums[j] <= nums[i]){
                    j--;
                }
                swap(nums,i,j);
            }
            reverse(nums, i+1);
            //Arrays.sort(nums, i+1, nums.length);
        }

        private void swap(int[] nums, int i, int j){
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        private void reverse(int[] nums, int start){
            int end = nums.length-1;
            while(start < end){
                swap(nums, start,end);
                start++;
                end--;
            }
        }

}
