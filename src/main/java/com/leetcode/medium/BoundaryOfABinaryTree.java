package com.leetcode.medium;

import java.util.*;

// https://leetcode.com/problems/boundary-of-binary-tree/
public class BoundaryOfABinaryTree {
     public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
     }


        /*
            - Starting from root.
            - Set of nodes visited. List<Integer> result.
            - Add root to visited and result.
            // - If root.left == null, Left boundary = root.
            - If root.left != null
                - find the left boundary. Traverse going as left as posible to a leaf node starting root.
            - Add all nodes in left boundary(which were not visited) to result.



           - Add all the leaves doing DFS and when encountering a leaf, add it to the result if that node was not visited.



            - If root.right ==null, right boundary = root;
            - else find the right boundary. Traverse going as right as possible to a leaf node starting root.
            - Add the right boundary to the stack if not visited.
            - Remove element from the stack to add to the final result;
        */
        public List<Integer> boundaryOfBinaryTree(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            if(root == null) return res;
            Set<TreeNode> seen = new HashSet<>();
            Deque<Integer> stack = new ArrayDeque<>();
            seen.add(root);
            res.add(root.val);
            if(root.left != null)
                addLeftBoundary(root.left, seen, res);
            recTraverse(root, seen, res);
            if(root.right != null)
                addRightBoundary(root.right, seen, res, stack);
            while(!stack.isEmpty()){
                res.add(stack.pop());
            }
            return res;
        }

        private void recTraverse(TreeNode curr, Set<TreeNode> seen, List<Integer> res){
            if(curr == null) return;
            if(curr.left == null && curr.right == null && !seen.contains(curr))
            {
                seen.add(curr);
                res.add(curr.val);
                return;
            }
            recTraverse(curr.left, seen, res);
            recTraverse(curr.right, seen, res);
        }


        private void addLeftBoundary(TreeNode root, Set<TreeNode> seen, List<Integer> res){
            if(root == null) return;

            if(!seen.contains(root))
            {
                seen.add(root);
                res.add(root.val);
                System.out.println("Adding node to the list: " + root.val);
            }
            if(root.left != null)
                addLeftBoundary(root.left, seen, res);
            else
                addLeftBoundary(root.right, seen, res);
        }


        private void addRightBoundary(TreeNode root, Set<TreeNode> seen, List<Integer> res, Deque<Integer> stack){
            if(root == null) return;
            if(!seen.contains(root))
            {
                seen.add(root);
                stack.push(root.val);
            }
            if(root.right != null)
                addRightBoundary(root.right, seen, res, stack);
            else
                addRightBoundary(root.left, seen, res, stack);
        }


    public boolean isLeaf(TreeNode t) {
        return t.left == null && t.right == null;
    }

    public void addLeaves(List<Integer> res, TreeNode root) {
        if (isLeaf(root)) {
            res.add(root.val);
        } else {
            if (root.left != null) {
                addLeaves(res, root.left);
            }
            if (root.right != null) {
                addLeaves(res, root.right);
            }
        }
    }

    // More elegant/faster LC solution. Probably because its the iterative version?
    public List<Integer> boundaryOfBinaryTreeLC(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        if (!isLeaf(root)) {
            res.add(root.val);
        }
        TreeNode t = root.left;
        while (t != null) {
            if (!isLeaf(t)) {
                res.add(t.val);
            }
            if (t.left != null) {
                t = t.left;
            } else {
                t = t.right;
            }

        }
        addLeaves(res, root);
        Stack<Integer> s = new Stack<>();
        t = root.right;
        while (t != null) {
            if (!isLeaf(t)) {
                s.push(t.val);
            }
            if (t.right != null) {
                t = t.right;
            } else {
                t = t.left;
            }
        }
        while (!s.empty()) {
            res.add(s.pop());
        }
        return res;
    }

}
