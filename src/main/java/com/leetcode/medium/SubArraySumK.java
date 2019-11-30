package com.leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/subarray-sum-equals-k/
public class SubArraySumK {

    /*
    I/P
    - arr  len < 20K
    - -1000< num < 1000
    - -1e7 (-10^7) < Sum < 1e7 (10^7)
    - How big I/P array? What kind of elements? Integers, Floating pts etc.
    How big each number is? How big is given Sum? Integer, long, BigInteger.
    - Can have dups nums and dups resulting arrays??
    - Sorted or not?
    - Brute force :
        (2^N) subsets of length 1 - N O(N) (Wrong.)
        Total O (2^N * N)
    -
        - Sort array(NlogN) // Can't sort the array(WRONG)
    - Starting at each index i from 0 - N-1 Consider prefix arrays.
        - N^2 prefix arrays of length N. O(N^3)
    - At each element, the contiguous subarrays ending are gonna be, all the ending contiguous subarrays ending at previous index plus this element to each of those sub arrays. Also, for our purpose, instead of storing list of list of numbers, we can store the list of list of sums.
    - what happens in case of no match? 0?
    - what happens in case of emtpy or null input? return 0?
    O(N^2) solution.


    O/P
    - Count of continuous subarrays whos sum matches given sum.


    */

    /*
    [1,1,1] k =2
    sumsAtIdx : {{1},{1,2},{1,2,3}} count : 1
    */

        public int subarraySum2(int[] nums, int k) {
            int count = 0;
            if(nums == null || nums.length == 0) return count;
            List<List<Integer>> sumsAtIdx = new ArrayList<>();
            sumsAtIdx.add(new ArrayList<>());
            sumsAtIdx.get(0).add(nums[0]);
            if(nums[0] == k) count++;
            for(int i = 1 ; i < nums.length; i++){
                List<Integer> sumsAtCurrIdx = new ArrayList<>();
                sumsAtCurrIdx.add(nums[i]);
                if(nums[i] == k) count++;
                List<Integer> sumsAtLastLevel = sumsAtIdx.get(i-1);
                for(int lastSum : sumsAtLastLevel) {
                    int contiguousSum = nums[i] + lastSum;
                    if(contiguousSum == k) count++;
                    sumsAtCurrIdx.add(contiguousSum);
                }
                sumsAtIdx.add(sumsAtCurrIdx);
            }
            return count;
        }

        public int subarraySum3(int[] nums, int k) {
            int count = 0;
            if(nums == null || nums.length == 0) return count;
            int[] sum = new int[nums.length+1];
            sum[0]=0;
            for(int i= 1; i <= nums.length;i++)
                sum[i] = sum[i-1] + nums[i-1];
            for(int start = 0; start < nums.length;start++){
                for(int end= start+1; end <= nums.length; end++){
                    if(sum[end]-sum[start] == k)
                        count++;
                }
            }
            return count;
        }

        public int subarraySum4(int[] nums, int k){
            int count = 0;
            if(nums != null){
                for(int start= 0 ; start < nums.length; start++){
                    int sum = 0;
                    for(int end = start; end < nums.length; end++){
                        sum += nums[end];
                        if(sum == k)
                            count++;
                    }
                }
            }
            return count;
        }

        public int subarraySum(int[] nums, int k) {
            int count = 0;
            if(nums == null) return count;
            Map<Integer,Integer> sumCount = new HashMap<>();
            sumCount.put(0,1);
            int cumSum = 0;
        /*
        3, 4, 7, 2, -3, 1, 4, 2 // k
        Map { 0:1, 3:1, 7:1, 14:2,16:1,13:1    }
        count = 2
        */
            for(int i = 0 ; i < nums.length; i++){
                cumSum += nums[i];
                count += sumCount.getOrDefault(cumSum-k,0);
                if(!sumCount.containsKey(cumSum))
                    sumCount.put(cumSum,1);
                else
                    sumCount.put(cumSum, sumCount.get(cumSum)+1);
            }
            return count;
        }

}
