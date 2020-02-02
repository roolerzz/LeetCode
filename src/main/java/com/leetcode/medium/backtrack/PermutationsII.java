package com.leetcode.medium.backtrack;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/permutations-ii/
public class PermutationsII {
        public List<List<Integer>> permuteUnique(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            boolean[] used = new boolean[nums.length];
            recurse(nums, res, new ArrayList<>(), used);
            return res;
        }

        private void recurse(int[] nums, List<List<Integer>> res, List<Integer> currList, boolean[] used) {
            if(currList.size() == nums.length) {
                res.add(new ArrayList<>(currList));
            }
            for(int i=0; i < nums.length; i++){
                // Handles duplicates.
                // used[i] = current element already being used.
                // i > 0 && nums[i] == nums[i-1] && !used[i-1] is for when say in [1,1,2] for the first 1 as starting point you
              // already generated [1,1,2], and for 2nd point as starting, you shouldn't do that to avoid dups [1,1,2];
                if(used[i] || (i > 0 && nums[i] == nums[i-1] && !used[i-1])) continue;
                currList.add(nums[i]);
                used[i] = true;
                recurse(nums, res, currList, used);
                used[i]=false;
                currList.remove(currList.size()-1);
            }
        }


}
