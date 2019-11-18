package com.leetcode.medium.backtrack;

import java.util.*;


//https://leetcode.com/explore/interview/card/top-interview-questions-medium/109/backtracking/795/
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


//	Write a method to compute all permutations of a string. CTCI 8.4
	    public Queue<String> permute2019(String str) {
			if(str == null ) return null;
			Queue<String> results = new ArrayDeque<>();
			if (str.length() == 0)  {
				results.add("");
				return results;
			}
			recPermute2019(str, 0, results);
	    	return results;
		}

		private void recPermute2019(String str, int pos, Queue<String> result){
	    	if(pos == str.length()) {
	    		result.add("");
	    		return;
			}
	    	recPermute2019(str, pos+1, result);
			char ch = str.charAt(pos);
			insertAtAllPos(result, ch);
		}

		public Queue<String> permuteIteratively2019(String str) {
	    	if(str == null) return null;
	    	Queue<String> results = new ArrayDeque<>();
	    	results.add("");
			for(int i = 0 ; i < str.length(); i++) {
				insertAtAllPos(results, str.charAt(i));
			}
			return results;
		}

		private void insertAtAllPos(Queue<String> res, char ch){
	    	int resultSize = res.size();
	    	for(int i = 0 ; i < resultSize; i++) {
	    		String front = res.remove();
	    		for(int j = 0 ; j <= front.length(); j++) {
					StringBuilder sb = new StringBuilder(front);
					sb.insert(j, ch);
					res.add(sb.toString());
				}
			}
		}

  public static void main(String[] args) {
    Permutations p = new Permutations();
    Queue<String> recRes = p.permute2019("234");
	  System.out.println(recRes);
	  Queue<String> iterRes = p.permuteIteratively2019("234");
    System.out.println(iterRes);
  }
}
