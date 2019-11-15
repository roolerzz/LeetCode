package com.leetcode.medium;


// https://leetcode.com/problems/inorder-successor-in-bst/
public class InorderSuccessorInBST {

    private class TreeNode {
//        int val;
        TreeNode left, right, parent;
    }

    public TreeNode inorderSuccesorBst(TreeNode current) {
        if (current == null) return null;

        if (current.right != null) return leftMostChild(current.right);

        else {
            TreeNode parent;
            while((parent = current.parent) != null) {
                if(parent.left == current) return parent;
                current = parent;
            }
        }
        return null;
    }

    private TreeNode leftMostChild(TreeNode current) {
        if (current == null) return null;
        while(current.left != null)
            current = current.left;
        return current;
    }

}
