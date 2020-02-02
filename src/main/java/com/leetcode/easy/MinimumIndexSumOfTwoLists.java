package com.leetcode.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// https://leetcode.com/problems/minimum-index-sum-of-two-lists/
public class MinimumIndexSumOfTwoLists {
    /*
    - For each of the like List, create a map of Restraunt Name and its index position. m1, and m2.
    - Create an array of List of strings for buckets. Index of array represent the index sum. Size,  list.size() + list2.size()-1. for indexing indexes b/w 0 , list1.size()-1 + list2.size()-1;
    - For each string in the smaller list:
        - If the string is present in both the maps, add string to the bucket.
    - Start iterating on the bucket from smallest index, if list not null,
    return the elements of the list as array.

    */
    public String[] findRestaurant1(String[] list1, String[] list2) {
        if(list1 == null || list2 == null || list1.length == 0 || list2.length ==0 ) return new String[] {};
        if(list1.length > list2.length) return findRestaurant(list2,list1);
        Map<String, Integer> m2 = new HashMap<>();
        for(int i=0; i < list2.length; i++)
            m2.put(list2[i],i);
        List<String>[] buckets = new ArrayList[list1.length + list2.length-1];
        for(int i=0; i < list1.length; i++ ){
            if(!m2.containsKey(list1[i])) continue;
            int idxSum = i + m2.get(list1[i]);
            if(buckets[idxSum] == null)
                buckets[idxSum] = new ArrayList<>();
            buckets[idxSum].add(list1[i]);
        }
        int idx = 0;
        for(int i=0; i < buckets.length; i++){
            if(buckets[i] == null) continue;
            idx = i;
            break;
        }
        return buckets[idx].toArray(new String[buckets[idx].size()]);
    }


    public String[] findRestaurant(String[] list1, String[] list2) {
        if(list1 == null || list2 == null || list1.length == 0 || list2.length ==0 ) return new String[] {};
        if(list1.length > list2.length) return findRestaurant(list2,list1);
        Map<String, Integer> m2 = new HashMap<>();
        for(int i=0; i < list2.length; i++)
            m2.put(list2[i],i);
        List<String> res = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        for(int i=0; i < list1.length; i++ ){
            if(!m2.containsKey(list1[i])) continue;
            int idxSum = i + m2.get(list1[i]);
            if(idxSum <= min){
                if(idxSum < min) {
                    res.clear();
                    min = idxSum;
                }
                res.add(list1[i]);
            }
        }
        // int idx = 0;
        // for(int i=0; i < buckets.length; i++){
        //     if(buckets[i] == null) continue;
        //     idx = i;
        //     break;
        // }
        // return buckets[idx].toArray(new String[buckets[idx].size()]);
        return res.toArray(new String[res.size()]);
    }

}
