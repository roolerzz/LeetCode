package com.leetcode.easy;

// https://leetcode.com/problems/closest-binary-search-tree-value
public class ClosestBinarySearchTree {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
        public int closestValue(TreeNode root, double target) {
            TreeNode curr = root;
            int closest = root.val;
            while(curr != null) {
                closest = Math.abs(curr.val - target) < Math.abs(closest-target) ? curr.val : closest;
                curr = target < curr.val ? curr.left : curr.right;
            }
            return closest;
        }
}
