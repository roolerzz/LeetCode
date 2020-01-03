package com.leetcode.medium;

import java.util.*;

// https://leetcode.com/problems/binary-tree-vertical-order-traversal/
public class BinaryTreeVerticalOrderTraversal {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /*
    Approach 1:
      - DFS recursive soln. Row/Col remembered by implicit stack.
      - HashMap<Integer, Map<Integer, List<Integer>>>
        - Does help with the left to right ??
      - Keeping track of the min_col and Max_col to help iterate on the hashmap.

Approach 2:
      - Thinking doing a BFS that would help resolve the conflict for the same column from left to right.
        - Need a queue to process the children in order. Along with the nodes, their curr, row/col values needs to be remembered so that the final result array should have it sorted from smallest to largest.
        - While doing traversal, insert the following d.s. Number{int row, int col, int val};
        - sort the final input using row, col.
        - Iterate the sorted array to create List<List<Integer>>

    */

    private class Number{
        int row, col;
        TreeNode node;
        Number(int row, int col, TreeNode node){
            this.row = row;
            this.col = col;
            this.node = node;
        }
    }

    public List<List<Integer>> verticalOrder(TreeNode root) {
        if(root == null) return new ArrayList<>();
        Queue<Number> nodes = new LinkedList<>();
        nodes.offer(new Number(0, 0, root));
        List<Number> intermediateList = new ArrayList<>();
        while(!nodes.isEmpty()){
            Number curr = nodes.poll();
            intermediateList.add(curr);
            if(curr.node.left != null)
                nodes.offer(new Number(curr.row+1, curr.col-1,curr.node.left));
            if(curr.node.right != null)
                nodes.offer(new Number(curr.row+1, curr.col+1,curr.node.right));
        }
        Collections.sort(intermediateList, (a, b) -> {
            if(a.col != b.col) return a.col-b.col;
            return a.row-b.row;
        });
        List<List<Integer>> res = new ArrayList<>();
        int minCol=intermediateList.get(0).col;
        System.out.println(minCol);
        for(int i=0; i < intermediateList.size(); i++){
            Number curr =intermediateList.get(i);
            int idx = curr.col - minCol;
            if(res.size() <= idx)
                res.add(new ArrayList<>());
            List currColList = res.get(idx);
            currColList.add(curr.node.val);
        }
        return res;
    }

}
