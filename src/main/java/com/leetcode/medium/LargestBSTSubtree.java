package com.leetcode.medium;

// https://leetcode.com/problems/largest-bst-subtree/
public class LargestBSTSubtree {
    //    Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int largestBSTSubtree(TreeNode root) {
        /*
            Questions:
            - Does my whole tree exists into the memory.
            - What kind of elements does the tree holds. Are these Integer/String some other objects.
            - Duplicates and Integer MIN/MAX Value.
            Approach:
            - Global variable of largestBSTSize = 0;
            - Starting with root.O(n!)
                - Check if the subtree rooted at that node is a BST or not.
                - find length of the subtree rooted at that tree.
                - Compare the length with the largestBSTSize only if the rooted subtree is a BST or not.
            - For each node in the tree, find if its a BST rooted at that node.

            - A lot of repeat work is happening. like asking question like
            if the subtree rooted at that node is BST also asks the question about whether the subtree rooted at its left/right subtree is BST or not. if we can save the result to those queries.
            - Tuple{Node n; boolean isBST, int size}
        */
        if(root == null) return 0;
        largestBSTSub(root);
        return (int)largest;
    }

    long largest = 0;

    class Tuple {
        long size;
        long min;
        long max;
        Tuple(long size, long min, long max){
            this.size = size;
            this.min = min;
            this.max = max;
        }
    }

    private Tuple largestBSTSub(TreeNode root ){
        if(root == null) return new Tuple(0, Integer.MAX_VALUE+1l, Integer.MIN_VALUE-1l);
        Tuple left = largestBSTSub(root.left);
        Tuple right = largestBSTSub(root.right);
        if(left.size == -1 || right.size == -1 || root.val <= left.max || root.val >= right.min)
            return new Tuple(-1, 0, 0);
        long size = 1 + left.size + right.size;
        largest = Math.max(largest,size);
        return new Tuple(size, Math.min(left.min, root.val), Math.max(right.max,root.val));
    }


}
