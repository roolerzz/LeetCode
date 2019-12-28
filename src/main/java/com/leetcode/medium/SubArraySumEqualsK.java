package com.leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/subarray-sum-equals-k/
public class SubArraySumEqualsK {

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

    /*
    *   Idea here is, at any index i, Remember(save) sum(x:i) where x could be b/w 0 to i.
    *   Now going to i+1, for all the sums, sum(x:i+1) = sum(x:i) + nums[i+1]. If that sum equals k, increment the count.
    *   otherwise, keep on going forward until you reach the end of the array. Basically you use the sums computed at previous level
    *   to find the sums at the current level.
    *   This solution takes O(N^2) time(N elements and each element can have atmost N sums), and O(N^2) sums.
    *   for i=0, 1 sum, i=1, 2 sum ... i = n = n+1 sum. so total = 1+2 + ... n+1 = (n+1)(n+2)/2 = O(N^2);
    * */
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


    /*
    * Idea here in 1 pass pre-calculate the cumulative sum of elements(from 0) upto the ith index.
    * Now, using this cumulative sum array, you can form any possible subarray sum in the array
    * (for various different pairs of) cumulative sums. If the difference b/w any pair of cumulative sum(some subarray sum)
    * adds up to the given subarray sum, increment count.
    *
    * Takes O(N) time to build cumulative array of size O(N), O(N^2) time for considering all pairs of cumulative sums.
    * So Time : O(N^2)
    * Space : O(N).
    * */
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

    /*
   The idea here is based on the cumulative sums until the index I. Idea is, If the cumulative Sums to index I(say SumI) equals cumulative sum to index J(sumI=sumJ), that means, subarraySum(i:j) was 0. Further extending the idea, say if Cumulative Sum X(SumX), SumX-k=SumI, that means the subarray(i:x) matches the sum that we are looking for. Also subarray(j:x) since subarray(i:j) was adding up to 0.
   We use a map sumCount, storing how many times a cumulative sum happend. Update the counts as we see the cumulative sum.
   everytime we first try to find out if cumulativesum-K exists already exists into the map, that means we can use the current indexX and the existing indexes(i,j, ... if the count of that cumulative sum was greater than 0) to form the given sum K(soi:x, j:x, )
   */
    public int subarraySum(int[] nums, int k){
        if(nums == null || nums.length ==0) return 0;
        Map<Integer, Integer> sumCount = new HashMap<>();
        sumCount.put(0,1);
        int sum=0, count=0;
        for(int num : nums){
            sum += num;
            count += sumCount.getOrDefault(sum-k,0);
            sumCount.put(sum,sumCount.getOrDefault(sum,0)+1);
        }
        return count;
    }

}
