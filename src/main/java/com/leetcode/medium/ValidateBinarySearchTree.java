package com.leetcode.medium;

import java.util.LinkedList;

// https://leetcode.com/problems/validate-binary-search-tree/
public class ValidateBinarySearchTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /*
    - elements? In memory?
    1. Recursive algorithm. O(N), O(N) DFS and checking if the current node doesn't violate BST property, check left and right child if exist.
    2. BFS. Check if current node doesn't violate BST property, add non-nil left and right child into queue for processing.O(n) O(n)
    - 1 elem is valid BST.
    - Nil root? is that valid i/p?
    - Equal elements?

    10,5,15,null, null, 6, 20
    */

        /* For every node, elements to the left of it, are lesser than it, and element to the right of it, are greater than the current value.*/
        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> lower = new LinkedList<>();
        LinkedList<Integer> upper = new LinkedList<>();

        public void update(TreeNode curr, Integer min, Integer max){
            stack.add(curr);
            lower.add(min);
            upper.add(max);
        }

        public boolean isValidBST4(TreeNode root) {
            update(root, null, null);

            while(!stack.isEmpty()){
                TreeNode curr = stack.poll();
                Integer min = lower.poll();
                Integer max = upper.poll();
                //System.out.println("Curr val is : " + curr.val + " and min is : " + min + " and max is : "+ max);
                if(curr == null) continue;
                if(min != null && curr.val <= min) return false;
                if(max != null && curr.val >= max) return false;
                update(curr.right, curr.val, max);
                update(curr.left, min,curr.val);
            }
            return true;
        }

        public boolean isValidBST(TreeNode root) {
            LinkedList<TreeNode> stack = new LinkedList<>();
            double inorder = Double.MIN_VALUE;

            while(!stack.isEmpty() && root != null){
                while(root != null) {
                    stack.push(root);
                    root = root.left;
                }
                root = stack.pop();
                if(root.val <= inorder) return false;
                inorder = root.val;
                root = root.right;
            }
            return true;
        }


        public boolean isValidBSTR(TreeNode root) {
            return isValidBSTRec(root, null, null);
        }

        private boolean isValidBSTRec(TreeNode curr, Integer min, Integer max){
            if(curr == null) return true;
            int val = curr.val;
            if(min != null && curr.val <= min) return false;
            if(max != null && curr.val >= max) return false;
            return isValidBSTRec(curr.left, min, val) && isValidBSTRec(curr.right, val, max);
        }


}
