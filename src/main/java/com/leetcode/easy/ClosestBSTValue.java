package com.leetcode.easy;

public class ClosestBSTValue {

	// https://leetcode.com/problems/closest-binary-search-tree-value/description/
	// Recursive
	public int closestValueRec(TreeNode root, double target) {
		if (root == null)
			return -1;
		TreeNode child = (root.val > target) ? root.left : root.right;
		if (child == null)
			return root.val;
		int k = closestValue(child, target);
		return Math.abs(root.val - target) < Math.abs(k - target) ? root.val : k;
	}

	// Iterative
	public int closestValue(TreeNode root, double target) {
		int result = root.val;
		while (root != null) {
			if (Math.abs(root.val - target) < Math.abs(result - target))
				result = root.val;
			root = root.val > target ? root.left : root.right;
		}
		return result;
	}

}
