package com.leetcode.easy;

import java.util.Stack;

//https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
public class SortedArrayToBST {

    private class Node {
        int val;
        Node left, right;
        Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public Node recursiveBST(int[] arr) {
        if (arr == null) return null;
        return recursivelyMakeBST(arr, 0, arr.length-1);
    }

    public Node recursivelyMakeBST(int[] arr, int start, int end) {
        if (end < start) return null;
        int mid = start + (end - start)/2; // avoids overflow.
        Node midNode = new Node(arr[mid], null ,null);
        midNode.left = recursivelyMakeBST(arr, start, mid-1);
        midNode.right = recursivelyMakeBST(arr, mid+1, end);
        return midNode;
    }


    // Thinking about how you will do the iterative soln, good way would be to think about
    // what was happening on the recursive solution, and see what values were remembered on the top of the stack.
    // Then try saving that same state on a stack(or a set of stack) and pop to get back to the saved state.

    // General idea here is, just like recursive soln flow, we will start with pair index(start,end) on the stack of
    // index pairs and a dummy node(node with a place holder value say 0) on the stack of nodes. We also need to
    // remember the middle nodes in that order.Pop the first pair of indexes. Find the mid, assign the value of
    // arr[mid] to the top of the node on the stack of nodes. Pop that node out. Create 2 dummy nodes and push it
    // on the top of stack nodes(right first then left) linked to the middle node popped in the last step.
    // Also push the pair of indexes that would be used to create those child sub tress in their
    // respective(right then left) order on indexes stack.

    private class IndexPair{
        int left, right;
        IndexPair(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    public Node iterativeBST(int[] arr) {
        Stack<IndexPair> indexPairStack = new Stack<>();
        Stack<Node> nodeStack = new Stack<>();

        Node root = new Node(0, null, null);
        IndexPair rootPair = new IndexPair(0, arr.length-1);
        indexPairStack.push(rootPair);
        nodeStack.push(root);

        while(!indexPairStack.isEmpty()){
            IndexPair curr = indexPairStack.pop();
            int start = curr.left;
            int end = curr.right;

            if(start > end){
                continue;
            }

            if(start == end){
                Node top = nodeStack.pop();
                top.val = arr[start];
                continue;
            }

            int mid = start + (end - start) /2;
            Node top = nodeStack.pop();
            top.val = arr[mid];
            top.right= new Node(0, null, null);
            nodeStack.push(top.right);
            indexPairStack.push(new IndexPair(mid+1, end));
            if(mid > start){
                top.left = new Node(0,null,null);
                indexPairStack.push(new IndexPair(start, mid-1));
            }
        }
        return root;
    }

//  public static void main(String[] args) {
//
//  }
}
