package com.leetcode.medium;

import java.util.Stack;

//https://leetcode.com/problems/sum-root-to-leaf-numbers/description/
public class SumRootToLeafBT {
	// Definition for a binary tree node.
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public int sumNumbers(TreeNode root) {
		Stack<TreeNode> nodes = new Stack<>();
		Stack<String> paths = new Stack<>();
		if (root != null) {
			nodes.push(root);
			paths.push("" + root.val);
		}
		int sum = 0;
		while (!nodes.isEmpty()) {
			TreeNode currentNode = nodes.pop();
			String currentPath = paths.pop();
			if (currentNode.right != null) {
				nodes.push(currentNode.right);
				paths.push(currentPath + currentNode.right.val);
			}
			if (currentNode.left != null) {
				nodes.push(currentNode.left);
				paths.push(currentPath + currentNode.left.val);
			}

			// For leaf node, add the paths until now.
			if (currentNode.left == null && currentNode.right == null)
				sum += Integer.valueOf(currentPath);
		}
		return sum;

	}

	public int sumNumbersRec(TreeNode root) {
		if (root == null)
			return 0;
		return recursiveHelper(root, 0);
	}

	private int recursiveHelper(TreeNode root, int sum) {
		if (root == null)
			return 0;
		sum = sum * 10 + root.val;
		if (root.left == null && root.right == null)
			return sum;
		return recursiveHelper(root.left, sum) + recursiveHelper(root.right, sum);
	}

}
