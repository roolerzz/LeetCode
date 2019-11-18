package com.leetcode.medium.backtrack;

import javafx.scene.SubScene;

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

	// Idea is at each index, you can either 1) Take the element at the index. Add it to the local list of elements. Continue generating the subsets
	// next position onwards  Or 2) skip the current element(by removing it from the current buffer/list) and generate the subset next position onwards.
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
	
	// Idea is Using a queue, you can do this solution iteratively. You can start with storing empty set in the result set.
	// For each element in the set, get the all the list in the result set, clone it, and add the current element in cloned list. Add the list of
	// cloned list back to the result set. O(2^n)
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

	public ArrayList<ArrayList<Integer>> subsets2019(int[] nums) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
	   	recSubsets2019(nums, 0, result );
	   	return result;
	}

	// For each element in the set, you can either 1) skip it, and get subsets with rest of elements, or 2)get subset with rest.
	// Once you get subset with skipping of the current element, get all the results clone it, then add the current elements in the clone.
	// All the cloned result(not with all the sets having current Element) to the original result set which has all the set w/o the current element.
	private void recSubsets2019(int[] nums, int currIdx, ArrayList<ArrayList<Integer>> res){
	   	if(currIdx >= nums.length){
	   		res.add(new ArrayList<>());
	   		return;
	   	}
	   	ArrayList<ArrayList<Integer>> clone = new ArrayList<>();
	   	// Getting subset after ignoring the current element.
	   	recSubsets2019(nums, currIdx+1, res);
	   	for(ArrayList<Integer> l : res) {
	   		ArrayList<Integer> clonedSubList = (ArrayList<Integer>)l.clone();
	   		clonedSubList.add(0,nums[currIdx]);
			clone.add(clonedSubList);
		}
		res.addAll(clone);
	}

  public static void main(String[] args) {
	  Subsets s = new Subsets();
	  List<List<Integer>> res = s.subsetsBackTrack(new int[]{1,2,3,4});
	  System.out.println(res);
	  ArrayList<ArrayList<Integer>> res2019 = s.subsets2019(new int[]{1,2,3,4});
	  System.out.println(res2019);
  }
}
