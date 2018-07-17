package com.leetcode.easy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;


/* -----------------------------------
 *  WARNING:
 * -----------------------------------
 *  Your code may fail to compile
 *  because it contains public class
 *  declarations.
 *  To fix this, please remove the
 *  "public" keyword from your class
 *  declarations.
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
class Solution2 {
	
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        
        TreeNode root = constructSubTree(preorder,inorder,0,preorder.length-1);
        return root;
    }

    private TreeNode constructSubTree(int[] preorder, int[] inorder, int lo, int hi){
        if(lo > hi) return null;
        int rootVal = preorder[lo];
        TreeNode root = new TreeNode(rootVal);
        if(lo == hi)return root;
        root.left = constructSubTree(preorder,inorder,lo+1,indexOf(inorder,rootVal));
        root.right = constructSubTree(preorder,inorder,indexOf(inorder,rootVal)+1,hi);
        return root;
    }
    
    private int indexOf(int[] arr, int val){
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == val)
                return i;
        }
        return -1;
    }
}

public class Test {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
          return new int[0];
        }
    
        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }
    
    public static String treeNodeToString(TreeNode root) {
        if (root == null) {
            return "[]";
        }
    
        String output = "";
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();
    
            if (node == null) {
              output += "null, ";
              continue;
            }
    
            output += String.valueOf(node.val) + ", ";
            nodeQueue.add(node.left);
            nodeQueue.add(node.right);
        }
        return "[" + output.substring(0, output.length() - 2) + "]";
    }
    
    public static void main(String[] args) throws IOException {
    	/*Solution3 solution3 = new Solution3();
    	solution3.subsets(new int[] {1,2,3});
    	*/
    	
    	PriorityQueue<Integer> maxPQ = new PriorityQueue<>(/*Collections.reverseOrder()*/);
    	
    	for(int i = 1 ; i < 10 ; i++) {
    		if(maxPQ.size() == 3 && i > maxPQ.peek()) {
    			System.out.println("Element Removed" + maxPQ.remove());
    		}
    		maxPQ.add(i);
    	}
    	while(!maxPQ.isEmpty())
    		System.out.println(maxPQ.poll());
    }
    
    static class Solution3 {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            recursiveHelper(result, new ArrayList<Integer>(), nums, 0 , nums.length-1);
            return result;
        }
        
        private void recursiveHelper(List<List<Integer>> result, List<Integer> list, int[] nums, int start, int end){
            result.add(new ArrayList<>(list));
            for(int i = start ; i <= end ; i++){
                list.add(nums[i]);
                recursiveHelper(result, list, nums, i+1, end);
                list.remove(list.size()-1);
            }
        }
    }
    
}