package com.leetcode.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// https://leetcode.com/problems/random-pick-with-weight/
public class RandomPickWithWeight {

    List<Integer> cumSum = new ArrayList<>();

    int max;

    Random random = new Random();

    public RandomPickWithWeight(int[] w) {
        for(int weight : w){
            max += weight;
            cumSum.add(max);
        }
    }

    public int pickIndex() {
        int target = random.nextInt(max);
        int lo = 0, high = cumSum.size()-1;
        while(lo != high){
            int mid = lo + (high-lo) /2;
            if(target >= cumSum.get(mid)) lo = mid+1;
            else high = mid;
        }
        return lo;
    }

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */

}
