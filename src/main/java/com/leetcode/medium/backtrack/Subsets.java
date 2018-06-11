package com.leetcode.medium.backtrack;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/explore/interview/card/top-interview-questions-medium/109/backtracking/796/
public class Subsets {
	
	// attempt 2 @ backtracking. So Far So Good.
	   public List<List<Integer>> subsetsBackTrack(int[] nums) {
	       List<List<Integer>> result = new ArrayList<>();
	       generateBacktracking(nums,result,new ArrayList<Integer>(),0);
	       return result;
	    }
	    
	    private void generateBacktracking(int[] arr, List<List<Integer>> result,List<Integer> current, int idx){
	        if(idx == arr.length){
	            result.add(new ArrayList<>(current));
	            return;
	        }
	        current.add(arr[idx]);
	        generateBacktracking(arr,result,current,idx+1);
	        current.remove(current.size()-1);
	        generateBacktracking(arr,result,current,idx+1);
	    }
	
	
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		result.add(new ArrayList<>());
		for (int n : nums) {
			int size = result.size();
			for (int i = 0; i < size; i++) {
				List<Integer> subset = new ArrayList<>(result.get(i));
				subset.add(n);
				result.add(subset);
			}
		}
		return result;
	}
}
