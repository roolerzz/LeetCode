package com.leetcode.medium;

import java.util.*;

// https://leetcode.com/problems/top-k-frequent-elements
public class TopKFrequentElements {
    /*
        Time:
        O(n)- countMap.
        O(n)- Count map to List
        O(nlogn) - Sorting list based on counts.
        O(Math.min(k,uniqueElements))

        Space: O(n)
    */
    public List<Integer> topKFrequent1(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();
        List<int[]> numCounts = new ArrayList<>();
        Map<Integer,Integer> countMap = new HashMap<>();
        for(int num : nums){
            countMap.putIfAbsent(num, 0);
            countMap.put(num, countMap.get(num)+1);
        }
        for(Map.Entry<Integer,Integer> entry : countMap.entrySet()){
            numCounts.add(new int[]{entry.getKey(), entry.getValue()});
        }
        Collections.sort(numCounts, (a, b) -> {
            return b[1]-a[1];
        });

        for(int i=0; i < numCounts.size() && i < k; i++){
            res.add(numCounts.get(i)[0]);
        }
        return res;
    }

    /*
    Time:
    O(N) - mapcount
    O(N) - Adding elements on the PQ.
    O(KlogN) - popping the max element k times from PQ.
    Total : O(N + KlogN)

    Space:
    O(N)
    */
    public List<Integer> topKFrequent2(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();
        Map<Integer,Integer> countMap = new HashMap<>();
        PriorityQueue<int[]> maxPQ = new PriorityQueue<int[]>((a,b) -> {
            return b[1]-a[1];
        });
        for(int num : nums){
            countMap.putIfAbsent(num, 0);
            countMap.put(num, countMap.get(num)+1);
        }
        for(Map.Entry<Integer, Integer> entry: countMap.entrySet()){
            maxPQ.add(new int[]{entry.getKey(), entry.getValue()});
        }
        while(!maxPQ.isEmpty() && k-- >0){
            res.add(maxPQ.poll()[0]);
        }
        return res;
    }

/*
* Bucket Sort. Runs in O(N) time with O(N) hashmap space.
* */
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();
        Map<Integer,Integer> countMap = new HashMap<>();
        List<Integer>[] buckets = new ArrayList[nums.length+1];
        for(int num : nums){
            countMap.put(num, countMap.getOrDefault(num,0)+1);
        }
        for(Map.Entry<Integer, Integer> entry : countMap.entrySet()){
            int freq = entry.getValue();
            int val = entry.getKey();
            if(buckets[freq] == null)
                buckets[freq] = new ArrayList<>();
            buckets[freq].add(val);
        }

        for(int i=buckets.length-1; i >= 0 && res.size() < k; i--){
            if(buckets[i] == null) continue;
            int noOfElems = buckets[i].size();
            int j=0;
            while(j < noOfElems && res.size() < k){
                res.add(buckets[i].get(j++));
            }
        }
        return res;
    }

}
