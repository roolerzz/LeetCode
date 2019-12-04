package com.leetcode.medium;

import java.util.*;

// https://leetcode.com/problems/binary-tree-right-side-view/
public class BinaryTreeRightSideView {

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
/*
                2
            3       5
         4     6   7  9
      8    12
    10 11
   9

result: 2, 5, 9, 12
// Stack: 3, 7
level: 0, 1, 2, 3
*/



/*
    - Recursive soln.
    - Maintain a list of the level that we have already seen.
    - For a given node,
       - If that level hasn't been seen, mark that seen and Add curr to the list.
       - If right child exist,  go right.
       - if left child exist, visit left.
       - else return.
*/

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        Queue<TreeNode> nodes = new ArrayDeque<>();
        nodes.offer(root);
        while(!nodes.isEmpty()){
            int size = nodes.size();
            while(size > 0){
                TreeNode currLvlNode = nodes.poll();
                if (currLvlNode.left != null) nodes.add(currLvlNode.left);
                if (currLvlNode.right != null) nodes.add(currLvlNode.right);
                if(size == 1) res.add(currLvlNode.val);
                size--;
            }
        }
        return res;
    }


    public List<Integer> rightSideViewIntertive(TreeNode root) {
        if(root == null) return new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        Set<Integer> levelsVisited = new HashSet<>();
        Stack<TreeNode> nodes = new Stack<>();
        Stack<Integer> nodeLevel = new Stack<>();
        nodes.push(root);
        nodeLevel.push(0);
        while(!nodes.isEmpty()){
            TreeNode curr = nodes.pop();
            int currLvl = nodeLevel.pop();
            if(!levelsVisited.contains(currLvl)){
                levelsVisited.add(currLvl);
                res.add(curr.val);
            }
            if(curr.left != null){
                nodes.push(curr.left);
                nodeLevel.push(currLvl+1);
            }
            if(curr.right != null) {
                nodes.push(curr.right);
                nodeLevel.push(currLvl+1);
            }
        }
        return res;
    }

    public List<Integer> rightSideViewR(TreeNode root) {
        if(root == null) return new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        Set<Integer> levels = new HashSet<>();
        rightSideViewRecursive(root, levels, res, 0);
        return res;
    }

    private void rightSideViewRecursive(TreeNode curr, Set<Integer> levels, List<Integer> res, int lvl){
        if(!levels.contains(lvl)) {
            levels.add(lvl);
            res.add(curr.val);
        }
        if(curr.right != null) rightSideViewRecursive(curr.right, levels, res, lvl+1);
        if(curr.left != null) rightSideViewRecursive(curr.left, levels, res, lvl+1);
    }
}
