package com.leetcode.easy;
// https://leetcode.com/problems/diameter-of-binary-tree/
public class DiameterOfBinaryTree {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
        int maxDia = 0;
        public int diameterOfBinaryTree(TreeNode root) {
            recDia(root);
            return maxDia;
        }

        private int recDia(TreeNode curr){
            if(curr == null) return 0;
            int left = recDia(curr.left);
            int right = recDia(curr.right);
            maxDia = Math.max(maxDia, left + right);
            return 1 + Math.max(left,right);
        }
}
