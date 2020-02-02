package com.leetcode.medium.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

// https://leetcode.com/problems/subsets-ii/
public class SubsetsII {
        public List<List<Integer>> subsetsWithDup(int[] nums) {
            // Set<List<Integer>> results = new HashSet<>(); // recurse1
            List<List<Integer>> results = new ArrayList<>();  // recurse
            Arrays.sort(nums);
            recurse(nums, results, new ArrayList<>(), 0);
            // return new ArrayList<>(results); // recurse 1;
            return results;
        }

        // Use a set to store the final result that would make sure duplicates aren't there.
        private void recurse1(int[]  nums, Set<List<Integer>> results, List<Integer> curr, int idx){
            if(idx == nums.length){
                results.add(new ArrayList<>(curr));
                return;
            }
            curr.add(nums[idx]);
            recurse1(nums, results, curr, idx+1);
            curr.remove(curr.size()-1);
            recurse1(nums, results, curr, idx+1);
        }

        // This looks more optimized, e.g. for the input 1, 2, 2 , idx = 1, so we iterate i from idx(1) to N-1(1)
        // Adding i=1 to the existing set {1}, returns {1,2}. Calling rec {1,2} 2, which results {1,2,2}.
        // Backtracking where existing set was {1} and idx = 1, i = 2, in this case we are not considering 2 again and
        // branching  thus we check if i is same as i-1 for i > idx.
        private void recurse(int[]  nums, List<List<Integer>> results, List<Integer> curr, int idx){
            results.add(new ArrayList<>(curr));
            for(int i = idx ; i < nums.length; i++){
                if(i > idx && nums[i-1] == nums[i]) continue;
                curr.add(nums[i]);
                recurse(nums, results, curr, i+1);
                curr.remove(curr.size()-1);
            }
        }
}
