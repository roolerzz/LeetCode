package com.leetcode.medium.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Permutations {
	// Queue Based Solution.
	    public List<List<Integer>> generatepermute(int[] nums) {
	        if(nums == null || nums.length ==0) return new ArrayList<List<Integer>>();
	        int n = nums.length;
	        Queue<List<Integer>> queue = new LinkedList<>();
	        queue.offer(new ArrayList<>());
	        while(--n >= 0){
	            int size = queue.size();
	            for(int i = 0 ; i < size ; i++){
	                List<Integer> entry = queue.poll();
	                for(int j = 0 ; j < nums.length; j++){
	                    if(!entry.contains(nums[j])){
	                        List<Integer> copy = new ArrayList<>(entry);
	                        copy.add(nums[j]);
	                        queue.offer(copy);
	                    }
	                    
	                }
	            }
	        }
	        return new ArrayList<>(queue);
	    }
	    
	    public List<List<Integer>> permute(int[] nums) {
	        List<List<Integer>> result = new ArrayList<>();
	        generateRecursively(nums, result, new ArrayList<Integer>());
	        return result;
	    }
	    
	    // Back Tracking Solution.
	    private void generateRecursively(int[] nums, List<List<Integer>> result, List<Integer> currList){
	        if(currList.size() == nums.length){
	            result.add(new ArrayList<>(currList));
	            return;
	        }
	        for(int i = 0 ; i < nums.length ; i++){
	            if(!currList.contains(nums[i])){
	                currList.add(nums[i]);
	                generateRecursively(nums,result,currList);
	                currList.remove(currList.size()-1);
	            }
	        }
	    }

}
