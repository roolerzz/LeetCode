package com.leetcode.medium;

import java.util.*;

public class BSTIterator {


    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /*
    Questions?
    - How is my input given? In memory or what?
    - What kind of elements does it contains, Numbers/Strings etc.
    - What should happen in case there isn't any element to iterate on.
    - What in case of just root or null root. HasNext -> false,
    Idea : 1
    - PreProcess/Store the Inorder traversal of the BST.Arr/List root idx o. Curr pointer = 1;
    - hasNext(currPtr < arr.size()).
    - Next() - return arr[i++]; // If the caller of the API is responsible for calling the hasNext() before calling next() in that case, we can safely just return it, instead of doing validations.
    - Wasting a lot of memory by keeping elements in memory. It would be faster becasue its pre-processed, but initial warmup calls would be slow, becasue of the time it would take to build the array.
    */
    List<Integer> elem;

    int curr = 0;

    // Remove return type for the constructor. Added because of multiple constructor cuz of multiple approaches implemented
    public void BSTIterator3(TreeNode root) {
        elem = new ArrayList<>();
        traverseInorder(root, elem);
    }


    /*
    curr: {}
    elem: {3,7,9,15,20}

    - Null root
    - Just Root(w/o children)
    - Left aligned tree.
    - right aligne tree
    - balance tree

    */
    private void traverseInorder(TreeNode curr, List<Integer> elem){
        if(curr == null) return;
        traverseInorder(curr.left, elem);
        elem.add(curr.val);
        traverseInorder(curr.right, elem);
    }

    /** @return the next smallest number */
    public int next3() {
        if(hasNext())
            return elem.get(curr++);
        return Integer.MIN_VALUE; // Depending upon the types of values that exists into the Tree.
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext3() {
        return curr < elem.size();
    }

    /*
    IDEA 2:
    - Stack stores the non-visited roots.
    HasNext():
    PreProcess:
    - Create a stack that can store nodes yet to be visited/returned.
    - Store all the left most nodes onto the stack. While loop until null.
    hasNext() = Stack.size()>0
    next() :
    - Pop the topmost element of the stack.
    - store the value to be returned.
    Subroutine:  If the right child exist, do a while loop on the right child to add all the left of that subtree.
    - Worst case space complexity would be O(N) flat tree. Perfectly balanced tree, log(N)
    - Time complexity O(N) worst case. No of nodes.
     */
    Deque<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        stack = new ArrayDeque<>();
        pushLeft(root);
    }

    private void pushLeft(TreeNode currNode){
        while(currNode != null)
        {
            stack.push(currNode);
            currNode = currNode.left;
        }
    }

    /** @return the next smallest number */
    public int next() {
        if(!hasNext()) throw new NoSuchElementException();
        TreeNode currNode = stack.pop();
        pushLeft(currNode.right);
        return currNode.val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */


}
