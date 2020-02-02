package com.leetcode.medium.backtrack;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/combination-sum
public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        recurse(candidates, res, new ArrayList<>(), target, 0, 0);
        return new ArrayList(res);
    }

    private void recurse(int[] candidates, List<List<Integer>> res, List<Integer> curr, int target, int currSum , int start){
        if(currSum > target)
            return;
        else if(currSum == target){
            res.add(new ArrayList<>(curr));
            return;
        }
        else {
            for(int i=start; i < candidates.length; i++){
                curr.add(candidates[i]);
                recurse(candidates, res, curr, target, currSum + candidates[i], i);
                curr.remove(curr.size()-1);
            }
        }
    }

}
