package com.leetcode.medium;

import java.util.Arrays;
import java.util.Random;

// https://leetcode.com/problems/kth-largest-element-in-an-array/
public class KthLargestElement {

  /*
      - Do a quick select for K.
         - Shuffle the array(for randomization, so that probability of equal subproblems is high.) O(N)
         - Choose the first element as the partition element. find the idx of partition elememt
            - if k-1 == pivot, return pivot element.
            - else if k-1 > pivot elem, go right.
            - else go left;
  */

    public static void main(String[] args) {
        KthLargestElement elem = new KthLargestElement();
        int[] nums= {3,2,1,5,6,4};
        int k = 2;
        System.out.println(k + "th largest element is : " + elem.findKthLargest(nums, k));

    }

    public int findKthLargest(int[] nums, int k) {
        if(nums == null || nums.length == 0 || k < 1) return Integer.MIN_VALUE;
        // shuffle(nums);
        return findKth(nums, nums.length-k, 0, nums.length-1);
    }

    private int findKth(int[] nums, int k, int start, int end ){
        while(start <= end){
            int pivot = findPivot(nums, start, end);
            if(pivot == k) return nums[pivot];
            else if (pivot > k) end = pivot-1;
            else start= pivot+1;
        }
        return -1;
    }

    private int findPivot(int[] nums, int start, int end){
        int pivotElem = nums[start];
        int i = start + 1;
        while(i <= end) {
            if(nums[i] >= pivotElem) {
                swap(nums,i,end);
                end--;
            }
            else i++;
        }
        swap(nums, start, i-1);
        return i-1;
    }

    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void shuffle(int[] nums){
        Random rand = new Random();
        System.out.print("Array before shuffling: ");
        System.out.println(Arrays.toString(nums));
        for(int i = 1; i< nums.length; i++){
            int r = rand.nextInt(i+1);
            swap(nums, r, i);
        }
        System.out.print("Array after shuffling: ");
        System.out.println(Arrays.toString(nums));
    }



    /*
    - Brute force.
    [3,2,3,1,2,4,5,5,6] k=4
    k=1 ->  i
    k=2 ->  j
    k=3 ->  k
    k=4 ->  x
    O(K*N) O(K)

    - SortArray O(NLOGN) O(LogN) return K-1;
    - Quick select.
        - N N/2 , N/4, N/8, .... after logN attempts 1.
        - NlogN worst case?
    */

}
