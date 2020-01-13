package com.leetcode.hard;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

// https://leetcode.com/problems/binary-tree-maximum-path-sum/
public class BinaryTreeMaximumPathSum {
    //    Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }


    /*
    Approach(recursive):
    - For each node starting from root, find the max_sum possible rooted at that that node, and involves the paths below it.
    - if the current node is null it contributes 0 to the sum.
    - calculate the max_sum that can come in the left subtree of the node.
    - calculate the max_sum that can come in the right subtree of the node.
    - Calculate the max_sum_node. It should be the max of, node.val, node.val+left, node.val+right, node.val+left+right, depending upon if both left/right were -ve, right sum was -ve, left sum was -ve or both were positive.
    - compare the max_sum_node to the global max_sum. Update the global max sum.
    - Return the max_sum on either branch(either left or right) of the node as we move 1 level above, as we can't just return the max_sum_node, as that may contains sums from both the branches of the node. But if we were to propogate the sum above, it should be the max sum on the path below, not the node, as we would be connecting the path.
    */

    int maxSum = Integer.MIN_VALUE;
    /*
        Time Complexity: O(N)
        Space Complexity : O(N) implicit stack worst case, when straight tree.(all children on either side.)
    */
    public int maxPathSum2(TreeNode root) {
        maxPathSumRec(root);
        return maxSum;
    }
    private int maxPathSumRec(TreeNode node){
        if(node == null) return 0;
        int leftSum = maxPathSumRec(node.left);
        int rightSum = maxPathSumRec(node.right);
        int maxSumEitherSide = Math.max(node.val, Math.max(node.val+leftSum, node.val+rightSum));
        maxSum = Math.max(maxSum, Math.max(maxSumEitherSide,node.val+rightSum + leftSum));
        return maxSumEitherSide;
    }


    private static final int CHILDREN_UNPROCESSED = 1;
    private static final int CHILDREN_PROCESSED = 2;


    private class NodeState{
        TreeNode node;
        int state;
        NodeState(TreeNode node, int state){
            this.node = node;
            this.state = state;
        }
    }


    // Time Complexity : O(n) we process each node only once.
    // Space: O(n) for hashmap for each node and O(n) for stack for each node in the worst case.
    public int maxPathSum(TreeNode root) {
        Deque<NodeState> stack = new LinkedList<>();
        stack.push(new NodeState(root, CHILDREN_UNPROCESSED));
        Map<TreeNode, Integer> maxSumMap = new HashMap<>();
        maxSumMap.put(null, 0);
        int maxSum = Integer.MIN_VALUE;
        while(!stack.isEmpty()){
            if(stack.peek().state == CHILDREN_UNPROCESSED){
                NodeState top = stack.peek();
                if(top.node.left != null) stack.push(new NodeState(top.node.left, CHILDREN_UNPROCESSED));
                if(top.node.right != null) stack.push(new NodeState(top.node.right, CHILDREN_UNPROCESSED));
                top.state = CHILDREN_PROCESSED;
                continue;
            }
            NodeState toProcess = stack.pop();
            int leftSum = Math.max(maxSumMap.get(toProcess.node.left),0);
            int rightSum =Math.max(maxSumMap.get(toProcess.node.right),0);
            maxSum = Math.max(maxSum, Math.max(leftSum,rightSum) + toProcess.node.val);
            maxSumMap.put(toProcess.node,  Math.max(toProcess.node.val, Math.max(leftSum,rightSum)));
        }
        return maxSum;
    }


    // https://leetcode.com/problems/binary-tree-maximum-path-sum/discuss/39927/Iterative-Java-solution
    // just returns the nodes in post-order
    public Iterable<TreeNode> topSort(TreeNode root) {
        Deque<TreeNode> result = new LinkedList<>();
        if (root != null) {
            Deque<TreeNode> stack = new LinkedList<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode curr = stack.pop();
                result.push(curr);
                if (curr.right != null) stack.push(curr.right);
                if (curr.left != null) stack.push(curr.left);
            }
        }
        return result;
    }

    public int maxPathSum3(TreeNode root) {
        int result = Integer.MIN_VALUE;
        Map<TreeNode, Integer> maxRootPath = new HashMap<>(); // cache
        maxRootPath.put(null, 0); // for simplicity we want to handle null nodes
        for (TreeNode node : topSort(root)) {
            // as we process nodes in post-order their children are already cached
            int left = Math.max(maxRootPath.get(node.left), 0);
            int right = Math.max(maxRootPath.get(node.right), 0);
            maxRootPath.put(node, Math.max(left, right) + node.val);
            result = Math.max(left + right + node.val, result);
        }
        return result;
    }



}
